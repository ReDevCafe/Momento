package org.momento.TEST;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface PrismAnot
{
    String descPath();
}