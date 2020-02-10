package myqueue;

import java.util.Iterator;

public class TestMyQueue {

    public static void main(String[] args) throws Exception {

        MyQueue<Integer> queue = new MyQueue<Integer>();

        queue.add(44);
        queue.offer(45);
        queue.offer(33);
        queue.offer(15);
        queue.offer(154);
        queue.offer(75);

        for(Integer inter : queue){
            System.out.println(inter);
        }
        System.out.println();
        System.out.println(queue.size());

        while(queue.size() > 2){
            System.out.println(queue.poll());
        }
        //queue.remove();
        System.out.println();
        System.out.println(queue.size());

        Iterator<Integer> iter = queue.iterator();

        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.size());
        System.out.println(queue.peek());
        System.out.println(queue.size());
        System.out.println(queue.remove());
        System.out.println(queue.size());

        queue.offer(25);
        System.out.println(queue.peek());
        System.out.println(queue.size());
        queue.offer(125);
        System.out.println(queue.size());

        //queue.forEach(elem -> System.out.println(elem));



    }

}
