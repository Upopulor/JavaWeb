package cn.historyList;

import java.util.HashMap;
import java.util.Map;

import cn.entity.Book;

public class DBUtil {
	private static Map<String,Book> books = new HashMap<String,Book>();
	static {
		books.put("1", new Book("1","水浒",23,"井陉"));
		books.put("2", new Book("2","三国",27,"奥迪"));
		books.put("3", new Book("3","红楼",22,"张武"));
		books.put("4", new Book("4","西游",34,"飞科"));
	}
	public static Map<String,Book> getBooks(){
		return books;
	}
	public static Book findBookById(String id) {
		return  books.get(id);
	}
}
