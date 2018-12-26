package cn.historyList;

import java.util.HashMap;
import java.util.Map;

import cn.entity.Book;

public class DBUtil {
	private static Map<String,Book> books = new HashMap<String,Book>();
	static {
		books.put("1", new Book("1","ˮ�",23,"����"));
		books.put("2", new Book("2","����",27,"�µ�"));
		books.put("3", new Book("3","��¥",22,"����"));
		books.put("4", new Book("4","����",34,"�ɿ�"));
	}
	public static Map<String,Book> getBooks(){
		return books;
	}
	public static Book findBookById(String id) {
		return  books.get(id);
	}
}
