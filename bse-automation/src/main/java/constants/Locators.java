package constants;

public class Locators {
    // Tabs
    public static final String MARKET_WATCH_TAB = "//a[@id='mwatch' and normalize-space(text())='Market Watch']";
    public static final String TOP_GAINERS_TAB = "//a[@id='gainer' and normalize-space(text())='Gainers']";
    public static final String TOP_LOSERS_TAB = "//a[@id='loser' and normalize-space(text())='Losers']";

    // Tables
    public static final String MARKET_SUMMARY_TABLE = "//div[@id='mktwatch']//table";
    public static final String GAINERS_TABLE = "//div[@id='gain']//table";
    public static final String LOSERS_TABLE = "//div[@id='lose']//table";

    // Index value cell
    public static final String INDEX_VALUE = "//td[@id='tdssp' and contains(@class,'tdcolumn')]";

    // Popup close button
    public static final String POPUP_CLOSE_BUTTON = "//button[@class='btn-close' and @data-bs-dismiss='modal']";
}
