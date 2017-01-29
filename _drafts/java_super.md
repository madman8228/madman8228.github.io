#### super应用场景和隐藏现象
super关键字的使用场景：  
1. 子类隐藏了父类字段，可以使用super解决。  
 ```
class SuperClass 
{
  public string name = "SuperClass.name";
}

class SubClass extends SuperClass
{
  public int name = 18;//hiding superclass name
  public void doWork()
  {
    System.out.println(name);//18
    System.out.println(super.name);//SuperClass.name
  }
} 
```
2.在子类方法中调用父类被覆盖的方法，要用super
```
class SuperClass 
{
  public string name = "SuperClass.name";
  public void doWork()
  {
    System.out.println("SuperClass doWork");
  }
}

class SubClass extends SuperClass
{
  public int name = 18;//hiding superclass name
  public void doWork()
  {
    System.out.println(name);//18
    System.out.println(super.name);//SuperClass.name
  }
  publc void person()
  {
    super.doWork();//invoke SuperClass doWork
  }
} 
```
3.在子类构造函数中调用父类构造函数，必须使用super（super(实参)）。
