package spittr.data;

import java.util.List;

import spittr.Spittle;

public interface SpittleRepository {

  List<Spittle> findRecentSpittles();

  List<Spittle> findSpittles(long max, int count);
  
  Spittle findOne(long id);

  Spittle save(Spittle spittle);
  
  void update(Spittle spittle);
  
  void delete(Long id);

}
