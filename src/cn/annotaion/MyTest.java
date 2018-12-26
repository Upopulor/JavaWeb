package cn.annotaion;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//创建一个注解类，在名字前加@
@Retention(RetentionPolicy.RUNTIME)//把注解的生命时期定义到runtime
public @interface MyTest {

}
