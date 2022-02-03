package childrenprocessing;

public final class ChildStrategyFactory {
    public enum ChildType {
        Baby, Kid, Teen
    }
    private ChildStrategyFactory() { }
    /**
     * This method is used to create certain strategies
     * @param childType
     * the argument used to spot the right strategy
     */
    public static ChildStrategy createStrategy(final ChildType childType) {
        return switch (childType) {
            case Baby -> new BabyStrategy();
            case Kid -> new KidStrategy();
            case Teen -> new TeenStrategy();
        };
    }
}
