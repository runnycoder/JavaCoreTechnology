package com.icehan.list;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 最大堆实现
 * 堆是一棵特殊的完全二叉树
 * 左右子结点大于父节点的叫做小顶堆
 * 左右子结点小于父节点的叫做大顶堆
 * 二叉树一般使用链表结构来存储 但是完全二叉树比较特别
 * 对一个完全二叉树使用层序遍历将结点存储到一个数组结构中
 * 其父节点和左右子结点的索引有明显的关系
 * left_index = parent_index*2+1
 * right_index = parent_index*2+2
 * 比如 根结点坐标为0 left=1 right=2
 */
public class MaxHeap<E extends Comparable<E>>  {

    private final E[] elments ;
    private final AtomicInteger capacity = new AtomicInteger();

    public MaxHeap(E[] elments) {
        this.elments = elments;
    }

    public  boolean isFull(){
        return capacity.get()==elments.length;
    }

    public  boolean isEmpty(){
        return capacity.get()==0;
    }

    public boolean insert(E e){
        if(isFull()){
            return false;
        }
        int index = capacity.getAndIncrement();;
        while (index>0){
            int parentIndex = (index-1)>>>1;
            E parentE = elments[parentIndex];
            if(e.compareTo(parentE)<0){//插入元素比父节点元素小满足条件
                break;
            }
            elments[index] = parentE;
            index = parentIndex;
        }
        elments[index]=e;//上滤最终找到元素插入位置

        return  true;
    }

    /**
     * 弹出堆顶元素
     * 找到堆顶元素很简单
     * 主要是删除堆顶元素后续的调整操作
     * 由于堆是用数组结构存储的向前移动补位这种操作造成的影响太大
     * 所以我们选择在堆中寻找合适的元素替换的方式
     * 那么初始的元素应该选择那个呢? 答案是队尾元素
     * 拿着初始元素transientItem与左右子结点值较大的进行对比
     * 如果transientItem>child=max(leftChild,right)说明符合最大堆的定义(parent结点大于左右子结点)
     * 如果transientItem>child=max(leftChild,rightChild)说明需要进行调整 parent位置设置为child ;parent = child_index;
     * 以最大子结点作为根结点继续下滤 这样调整对整个堆的影响较小 最终找到合适的位置我们将transientItem放上去
     * @return
     */
    public E peek(){
        if(isEmpty()){
            return null;
        }
        int parent = 0;
        int childIndex ;

        E maxItem = elments[parent];
        int capacityNum = capacity.getAndDecrement()-1;
        E transientItem = elments[capacityNum];

        //调整剩余的元素位置
        while ((parent*2+1)<capacityNum){
            childIndex = 2*parent+1;
            if(childIndex!=capacityNum&&elments[childIndex].compareTo(elments[childIndex+1])<0 ){//左结点小于右结点
                childIndex ++;
            }
            if(transientItem.compareTo(elments[childIndex])>0){//替换元素大于左右结点最大值 那当前parentIndex就是插入位置
                break;
            }else{
                elments[parent] = elments[childIndex];
            }
            parent = childIndex;//以最大子结点为根继续向下索引合适位置
        }
        elments[parent] = transientItem;
        elments[capacityNum] = null;
        return maxItem;
    }

    /**
     * 对指定位置的元素进行调整
     * @param index
     */
    public void maxPercDown(int index){
        E transientItem = elments[index];
        int capacityNum = capacity.get()-1;
        int parent=index,childIndex;
        //调整剩余的元素位置
        while ((parent*2+1)<capacityNum){//下滤寻找合适的插入位置
            childIndex = 2*parent+1;
            if(childIndex!=capacityNum&&elments[childIndex].compareTo(elments[childIndex+1])<0 ){//左结点小于右结点
                childIndex ++;
            }
            if(transientItem.compareTo(elments[childIndex])>0){//替换元素大于左右结点最大值 那当前parentIndex就是插入位置
                break;
            }else{
                elments[parent] = elments[childIndex];
            }
            parent = childIndex;//以最大子结点为根继续向下索引合适位置
        }
        elments[parent] = transientItem;
    }

    /**
     * (capacity.get()-1)/2
     * (capacity.get()-1)是完全二叉树按层序遍历最后一个元素的位置(最后一层最右边的叶结点)
     * 我们不需要从最后一层下滤调整 因为叶子结点没有子结点了
     * 所以我们需要找到最后一个结点的父节点 从这个结点开始进行调整(在这个父节点之前的结点都是有子结点的)
     * 最好对照这一个完全二叉树的图方便理解
     *
     */
    public void maxHeadAdjustment(){
        int i = (capacity.get()-1)/2;
        for (; i >0; i--) {
            maxPercDown(i);
        }
    }

    @Override
    public String toString() {
        return "MaxHeap{" +
                "elments=" + Arrays.toString(elments) +
                ", capacity=" + capacity +
                '}';
    }

    public static void main(String[] args) throws InterruptedException {
        Integer[] integers = new Integer[100];
        MaxHeap<Integer> integerMaxHeap = new MaxHeap<>(integers);
        integerMaxHeap.insert(1);
        integerMaxHeap.insert(2);
        integerMaxHeap.insert(4);
        integerMaxHeap.insert(3);
        integerMaxHeap.insert(6);
        integerMaxHeap.insert(5);
//        Integer peek = integerMaxHeap.peek();
//        System.out.println(peek);
        System.out.println(integerMaxHeap.capacity);
        integerMaxHeap.maxHeadAdjustment();
        System.out.println(integerMaxHeap.toString());

        Thread t1 = new Thread(() -> {
            System.out.println("111");
        });
        t1.join();
    }
}
