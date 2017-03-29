### java中两种创建线程的方式
* **继承Thread类方式**:继承线程类并重写run()方法,一般不常用这种方式,因为在JAVA中类只能单继承,用这种方法无法再继承其他类。 

``` 
Mythread.java

class Mythread extends Thread
{
    public void run() {
        System.out.println("hello");
    }
}

Test.java
public class Test
{
    public static void main(String[] args)
    {
        Mythread mythread = new Mythread();
        mythread.start();//启动线程,准备运行run()方法
    }
}

``` 
* **实现Runnable接口方式**:Runnable接口有一个run()方法,通过定义一个类实现run()方法,然后将类的对象传递给Thread对象,用Thread对象t来控制线程的启动。
线程的实现部分和控制部分是分开的,这种方式比较常用。 
``` 
Mythread.java

class MyRunnable implements Runnable
{
    public void run() {
        System.out.println("hello");
    }
}

Test.java
public class Test
{
    public static void main(String[] args)
    {
        MyRunnable myrunnable = new MyRunnable();
        Thread t = new Thread(myrunnable);
        t.start();//启动线程,准备运行run()方法
    }
}

``` 
