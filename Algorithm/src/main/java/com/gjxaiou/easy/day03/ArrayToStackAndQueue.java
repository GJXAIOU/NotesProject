package com.gjxaiou.easy.day03;

/**
 * @author GJXAIOU
 * 使用数组实现栈和队列结构
 */
public class ArrayToStackAndQueue {

    /**
     * 数组结构实现大小固定的栈
     */
    public static class ArrayToStack {
        private Integer[] arr;
        // index 当前指向栈的位置： 0 ~ size -1
        private Integer index;

        // 初始化数组
        public ArrayToStack(int initSize) {
            if (initSize < 0) {
                throw new IllegalArgumentException("The init size is less than 0");
            }
            arr = new Integer[initSize];
            index = 0;
        }

        // 在栈中压入一个数
        public void push(int value) {
            if (index == arr.length) {
                throw new ArrayIndexOutOfBoundsException("The stack is full");
            }
            // index：指向的是栈中下一个有空位置的数组下标
            // index 位置填上，然后 index++
            arr[index++] = value;
        }

        // 弹出一个栈顶的元素
        public Integer pop() {
            if (index == 0) {
                throw new ArrayIndexOutOfBoundsException("The stack is empty");
            }
            // 这里是 --index，因为 index 为下一个空位置的下标
            return arr[--index];
        }

        // 只将值返回给我，但是原来栈中该值仍然保存
        public Integer peek() {
            if (index == 0) {
                return null;
            }
            return arr[index - 1];
        }
    }


    /**
     * 数组结构实现大小固定的队列
     */
    public static class ArrayToQueue {
        private Integer[] arr;
        private Integer size;
        private Integer start;
        private Integer end;

        // 初始化队列
        public ArrayToQueue(int initSize) {
            if (initSize < 0) {
                throw new IllegalArgumentException("The init size is less than 0");
            }
            arr = new Integer[initSize];
            size = 0;
            start = 0;
            end = 0;
        }

        // 向队列中放入一个数
        public void push(int obj) {
            if (size == arr.length) {
                throw new ArrayIndexOutOfBoundsException("The queue is full");
            }
            size++;
            // 该数放在 end 的位置上，因为 end 位置是上下移动的；
            arr[end] = obj;
            // end 如果到底即 Length-1，就等于 0，从头开始写入数据，可以覆盖之前的元素；如果没有到底就 end + 1;
            end = (end == arr.length - 1) ? 0 : end + 1;
        }

        // 弹出队列头部元素
        public Integer poll() {
            if (size == 0) {
                throw new ArrayIndexOutOfBoundsException("The queue is empty");
            }
            size--;
            // 因为 start 位置要改变，所有使用临时变量 tmp 记录一下 start 位置，最终弹出的是原始 start 位置元素；
            int tmp = start;
            start = (start == arr.length - 1) ? 0 : start + 1;
            return arr[tmp];
        }

        // 取出队列头部的元素，但是队列不动
        public Integer peek() {
            if (size == 0) {
                return null;
            }
            return arr[start];
        }
    }
}

//
//class MyArrayToStackAndQueue {
//    class ToStack {
//        private Integer[] arr;
//        private Integer index;
//
//        public ToStack(int initSize) {
//            if (initSize > 0) {
//                this.arr = new Integer[initSize];
//                this.index = 0;
//            } else {
//                throw new RuntimeException("initSize is less than Zero");
//            }
//        }
//
//        public void push(int value) {
//            if (index == arr.length) {
//                throw new RuntimeException("The stack is full");
//            }
//            arr[index++] = value;
//        }
//
//        public Integer pop() {
//            if (index == 0) {
//                throw new RuntimeException("The stack is empty");
//            }
//            return arr[--index];
//        }
//
//        public Integer peek() {
//            if (index == 0) {
//                throw new RuntimeException("The stack is empty");
//            }
//            return arr[index - 1];
//        }
//    }
//
//    class ArrayToQueue {
//        private Integer[] arr;
//        private Integer start;
//        private Integer end;
//        private Integer size;
//
//        ArrayToQueue(int initSize) {
//            if (initSize <= 0) {
//                throw new IllegalArgumentException("the initSize is less than zero");
//            }
//            this.arr = new Integer[initSize];
//            this.start = 0;
//            this.end = 0;
//            this.size = initSize;
//        }
//
//        public void push(int value) {
//            if (size == arr.length) {
//                throw new ArrayIndexOutOfBoundsException("the queue is full");
//            }
//            size++;
//            arr[end] = value;
//            end = end == arr.length - 1 ? 0 : end + 1;
//
//        }
//
//        public Integer poll() {
//            if (size == 0) {
//                throw new ArrayIndexOutOfBoundsException("the queue is empty");
//            }
//            size--;
//            int temp = start;
//            start = start == arr.length - 1 ? 0 : start + 1;
//            return arr[temp];
//        }
//
//        public Integer peek() {
//            if (size == 0) {
//                throw new ArrayIndexOutOfBoundsException("the queue is empty");
//            }
//            return arr[start];
//        }
//    }
//}
