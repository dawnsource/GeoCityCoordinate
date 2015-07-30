package com.brett.http.geo.baidu;

@Deprecated
public class HtmlDecoder {

  /**
   *  "&#" 开头的html实体编码 转换成中文（其实只是将长度为5的整型作了转换，对开其它如英文实体会出现错误。）
   *
   * <option value="zh_CN">&#20013;&#25991; (&#31616;&#20307;)</option><option value="zh_TW">&#20013;&#25991; (&#32321;&#39636;)</option>
   *
   * @param str
   * @return
   */
  @Deprecated
  public static String decode(String str) {
    String[] tmp = str.split(";&#|&#|;");
    StringBuilder sb = new StringBuilder("");
    for (int i = 0; i < tmp.length; i++) {
      if (tmp[i].matches("\\d{5}")) {
        sb.append((char) Integer.parseInt(tmp[i]));
      } else {
        sb.append(tmp[i]);
      }
    }
    return sb.toString();
  }

}