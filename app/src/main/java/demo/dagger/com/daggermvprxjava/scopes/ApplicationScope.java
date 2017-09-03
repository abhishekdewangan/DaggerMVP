package demo.dagger.com.daggermvprxjava.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by abhishek on 03/09/17.
 */
@Scope
@Retention(RetentionPolicy.CLASS)
public @interface ApplicationScope {
}
