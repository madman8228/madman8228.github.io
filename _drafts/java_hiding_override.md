### java中的隐藏和覆盖
* **隐藏**:子类存在和父类一样的**静态方法**,称子类隐藏了父类的方法。
```  
class SuperClass
{
  public static func()
  {
    System.out.println("SuperClass");
  }
}

class SubClass extends SuperClass
{
  public static func()
  {
    System.out.println("SubClass");//子类隐藏了父类func方法
  }
}
```
* **覆盖**:子类存在和父一样的**非静态方法**,称子类覆盖了父类的方法。
