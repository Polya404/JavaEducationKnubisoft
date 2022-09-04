package knubisoft.base.reflection;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.LowerCase;

public class Test {
    @LowerCase
    private final Object x = 0;
    @Deprecated
    private final Object y = 1;
    @Deprecated
    private final Object z = 2;
    private final Object xz = 2;

}
