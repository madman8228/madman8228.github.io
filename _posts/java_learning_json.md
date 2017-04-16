### 介绍两种Android开发中生成和解析json的方法
* **Android SDK提供的Json库**

``` 

生成  Object:{"key":"value", "loop":[{"key1":"value1"}, {"key2":"value2"}]}
{
  JSONObject Object = new JSONObject();
  JSONObject loopObj = new JSONObject();
  JSONArray loopArray = new JSONArray();

  loopObj.put("key1", "value1");
  loopArray.put(loopObj);
  loopObj.put("key2", "value2");
  loopArray.put(loopObj);
  Object.put("key", "value");
  Object.put("loop", loopArray);
}

解析  Object:{"key":"value", "loop":[{"key1":"value1"}, {"key1":"value2"}]}

String key = Object.optString("key", "");
Log.d("tag", "test:" + key);
JSONArray loop = Object.optJSONArray("loop");
for(int i = 0; i < loop.length(); i++) {
  JSONObject obj = loop.optJSONObject(i);
  String value = obj.optString("key1");
  Log.d("tag", "test:" + value);
}
打印：
tag test: value
tag test: value1
tag test: value2


``` 
* **使用gson** 
``` 
生成  Object:{"key":"value", "loop":[{"key1":"value1"}, {"key2":"value2"}]}

class Base<T> {
    String key;
    ArrayList<T> loop;
    Base(String key, ArrayList<T> loop) {
        this.key = key;
        this.loop = loop;
    }
}
class SubObj {
    String key1;
    String key2;
    public SubObj(String key1, String key2) {
        this.key1 = key1;
        this.key2 = key2;
    }
}
ArrayList<SubObj> arrayList = new ArrayList<SubObj>();
arrayList.add(new SubObj("value1", "value2"));
Base<SubObj> Obj = new Base<SubObj>("value", arrayList);
gson = new Gson();
String jsonString = gson.toJson(Obj);
Log.d(TAG, "onCreate: jsonString=" + jsonString);
打印：
{"key":"value", "loop":[{"key1":"value1"}, {"key2":"value2"}]}


解析 JSONObject Object:{"key":"value", "loop":[{"key1":"value1"}, {"key1":"value2"}]}
class Base {
    String key;
    ArrayList<SubObj> loop = new ArrayList<SubObj>();//不支持泛型???
}

gson = new Gson();
Base jsonString = gson.fromJson(Object, Base.class);
jsonString.key
Log.d(TAG, "onCreate: jsonString.key=" + jsonString.key);
Log.d(TAG, "onCreate: jsonString.key=" + jsonString.loop.get(0).key1);
Log.d(TAG, "onCreate: jsonString.key=" + jsonString.loop.get(1).key1);
打印：
value
value1
value2


``` 
