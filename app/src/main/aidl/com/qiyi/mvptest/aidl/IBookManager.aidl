// IBookManager.aidl
package com.qiyi.mvptest.aidl;

import com.qiyi.mvptest.aidl.Book;
import com.qiyi.mvptest.aidl.IOnNewBookArrivedListener;

// Declare any non-default types here with import statements

interface IBookManager {
    List<Book> getBookList(); // 返回书籍列表
    void addBook(in Book book); // 添加书籍
    void registerListener(IOnNewBookArrivedListener listener); // 注册接口
    void unregisterListener(IOnNewBookArrivedListener listener); // 注册接口
}
