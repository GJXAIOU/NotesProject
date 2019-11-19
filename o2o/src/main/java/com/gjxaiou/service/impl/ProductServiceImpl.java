package com.gjxaiou.service.impl;

import com.gjxaiou.dao.ProductDao;
import com.gjxaiou.dao.ProductImgDao;
import com.gjxaiou.dto.ImageHolder;
import com.gjxaiou.dto.ProductExecution;
import com.gjxaiou.entity.Product;
import com.gjxaiou.entity.ProductImg;
import com.gjxaiou.enums.ProductStateEnum;
import com.gjxaiou.exception.ProductOperationException;
import com.gjxaiou.service.ProductService;
import com.gjxaiou.util.ImageUtil;
import com.gjxaiou.util.PageCalculator;
import com.gjxaiou.util.PathUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-11-04-20:53
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    /**
     * 根据商品 Id 查询商品
     *
     * @param productId
     * @return
     */
    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductByProductId(productId);
    }

    /**
     * 获取商品列表
     *
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        ProductExecution productExecution = new ProductExecution();
        // 页码转换成数据库的行，并调用dao层取回指定页码的商品列表
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(productCondition, rowIndex,
                pageSize);
        int count = productDao.queryProductCount(productCondition);
        productExecution.setProductList(productList);
        productExecution.setCount(count);

        return productExecution;
    }

    /**
     * 增加商品
     * 步骤一：处理缩略图，获取缩略图的相对路径并赋值给 product；
     * 步骤二：向 tb_product 中写入商品信息，同时可以获取 productId（因为设置了 useGeneratedKeys="true"）
     * 步骤三：结合 productId 批量处理商品详情图
     * 步骤四：将商品详情图列表批量插入 tb_product_img 中
     *
     * @param product
     * @param thumbnail
     * @param productImageHolderList
     * @return
     * @throws ProductOperationException
     */
    @Override
    public ProductExecution addProduct(Product product, ImageHolder thumbnail,
                                       List<ImageHolder> productImageHolderList) throws ProductOperationException {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            // 给商品赋值默认的系统属性
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            // 同时商品默认为上架状态（即前端能看见）
            product.setEnableStatus(1);

            // 处理并添加缩略图
            if (thumbnail != null) {
                addThumbnail(product, thumbnail);
            }
            // 插入商品
            try {
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum <= 0){
                    throw new ProductOperationException("创建商品失败");
                }
            }catch (Exception e){
                throw new ProductOperationException("创建商品失败" + e.toString());
            }

            // 若商品详情图不为空，则添加
            if (productImageHolderList != null && productImageHolderList.size() > 0){
                addProductImgList(product,productImageHolderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS,product);
        }else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    /**
     *  修改店铺
     *     步骤一：如果缩略图参数有值，则处理缩略图
     *     步骤二：若原来存在缩略图则先删除再添加新的缩略图，之后获取缩略图相对路径并赋值给 product
     *     步骤三：若商品详情图列表参数有值，对商品详情图片列表进行同样的操作
     *     步骤四：将 tb_product_img下面的该商品原来的商品详情图记录全部清除
     *     步骤五：更新 tb_product_img 以及 tb_product 的信息
     * @param product
     * @param thumbnail
     * @param productImgHolderList
     * @return
     * @throws ProductOperationException
     */
    @Override
    @Transactional
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail,
                                          List<ImageHolder> productImgHolderList) throws ProductOperationException {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            // 给商品设置默认属性
            product.setLastEditTime(new Date());
            // 若商品缩略图不为空且原有缩略图不为空则删除原有缩略图并添加
            if (thumbnail != null) {
                // 先获取一遍原有的信息，因为原来的信息有原图片地址
                Product tempProduct = productDao.queryProductByProductId(product.getProductId());
                if (tempProduct.getImgAddr() != null) {
                    ImageUtil.deleteFileOrDirectory(tempProduct.getImgAddr());
                }
                addThumbnail(product, thumbnail);
            }
            // 如果有新存入的商品详情图，则先删除原来的，再添加新的
            if (productImgHolderList != null && productImgHolderList.size() > 0) {
                deleteProductImgList(product.getProductId());
                addProductImgList(product, productImgHolderList);
            }
            try {
                // 更新商品信息
                int effectedNum = productDao.updateProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("更新商品信息失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            } catch (Exception e) {
                throw new ProductOperationException("创建商品详情图片失败;" + e.toString());
            }
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }


    /**
     * 给店铺增加详情图片
     * @param product
     * @param thumbnail
     */
    private void addThumbnail(Product product, ImageHolder thumbnail) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        product.setImgAddr(thumbnailAddr);
    }


    /**
     * 批量增加详情图
     * @param product
     * @param productImgHolderList
     * @throws ProductOperationException
     */
    public void addProductImgList(Product product, List<ImageHolder> productImgHolderList) throws ProductOperationException{
        // 获取图片存储路径，这里直接存放到相应店铺的文件夹底下
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();
        // 遍历图片一次处理，添加进productImg实体类中
        for (ImageHolder productImgHolder : productImgHolderList) {
            String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }
        // 如果确实是有图片需要添加的，就执行批量添加操作
        if (productImgList.size() > 0) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品详情图片失败" + e.toString());
            }
        }
    }

    public void deleteProductImgList(long productId){
        // 根据productId获取原来的图片
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        // 删除原来的图片
        for (ProductImg productImg : productImgList) {
            ImageUtil.deleteFileOrDirectory(productImg.getImgAddr());
        }
        // 删除数据库里原有的图片的地址
        productImgDao.deleteProductImgByProductId(productId);
    }

}
