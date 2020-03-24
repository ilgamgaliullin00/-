package lab6.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//Эта аннотация позволит вам указать те java элементы, к которой аннотация должна быть применена
@Retention(RetentionPolicy.RUNTIME)//Эта аннотация позволит вам указать, когда аннотация будет доступна
public @interface Entity {
  String name() default "";
}