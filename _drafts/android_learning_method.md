### Android中时间字符串转换整形数据

``` 
  应用

  Date now = new Date();
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  String time = dateFormat.format( now );
  Log.d(TAG, "onCreate: time:" + time);
  long l = CommonUtils.convertStringDateToLong(time);
  Log.d(TAG, "onCreate: l:" + l);
  String str = CommonUtils.convertMillisToDate(l);
  Log.d(TAG, "onCreate: str:" + str);

    /**
     *
     * @param strDate must be align with SimpleDateFormat() prarmeter
     * @return a Long type of date
     */
    public static long convertStringDateToLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate.getTime();
    }

    /**
     *
     * @param millis millis of date
     * @return
     */
    public static String convertMillisToDate(long millis) {
        Date date = new Date(millis);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = formatter.format(date);
        return str;
    }

``` 
###
