### java中的单例模式--饿汉式
* **饿汉式**:在类构造函数之前创建对象。 

``` 
SingletonDemo.java

class ArrayUtil
{
  
    private static ArrayUtil instance = new ArrayUtil();//自己先创建一个对象
    
    private ArrayUtil()；//防止外部通过构造函数创建新对象
    
    public static ArrayUtil getInstance()
    {
        return instance; 
    }
    
    public void sort(int[] arr)
    {
        System.out.println("sort");
    }
}

class SingletonDemo
{
    public static void main(String[] args)
    {
        ArrayUtil.getInstance().sort(null);
    }
}


``` 
