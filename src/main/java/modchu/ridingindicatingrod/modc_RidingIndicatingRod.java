package modchu.ridingindicatingrod;import java.io.File;import java.util.ArrayList;import java.util.List;import java.util.concurrent.ConcurrentHashMap;import modchu.lib.Modchu_AS;import modchu.lib.Modchu_CastHelper;import modchu.lib.Modchu_Config;import modchu.lib.Modchu_Debug;import modchu.lib.Modchu_IItem;import modchu.lib.Modchu_Main;import modchu.lib.Modchu_Reflect;public class modc_RidingIndicatingRod {	public static boolean useAddChatMessage = true;	public static boolean useOriginalIcon = true;	// ~164	public static int ridingIndicatingRodID = 17660;	private static final int requestModchuLibVersion = 1702;	public static final String versionString = "9";	public static Modchu_IItem itemInstance;	private static boolean debugMessage = true;	private static modc_RidingIndicatingRod ridingIndicatingRodInstance;	private static File whiteListfile;	private static File ngListfile;	public static List<String> whiteList = new ArrayList<String>();	public static List<String> ngList = new ArrayList<String>();	private static File mainCfgfile;	public static String itemName;	private String packageName;	public String getVersion() {		return versionString;	}	public void load() {		boolean debug = false;		int i1 = Modchu_Main.getVersionStringConversionInt(Modchu_Main.lastIndexProcessing(Modchu_Main.getVersion(), "-"));		if (i1 < requestModchuLibVersion) {			Modchu_Debug.systemLogDebug("modc_RidingIndicatingRod ModchuLib Version is old !! VersionInt="+i1);			Modchu_Main.setRuntimeException("modc_RidingIndicatingRod ModchuLib Version is old !!");			return;		} else {			if (debug) Modchu_Debug.lDebug("modc_RidingIndicatingRod ModchuLib VersionInt="+i1);		}		whiteListfile = new File(Modchu_Main.cfgdir, ("RidingIndicatingRod_whiteList.cfg"));		ngListfile = new File(Modchu_Main.cfgdir, ("RidingIndicatingRod_NGList.cfg"));		mainCfgfile = new File(Modchu_Main.cfgdir, ("RidingIndicatingRod.cfg"));		ridingIndicatingRodInstance = this;		loadcfg();		itemName = "RidingIndicatingRod";		Object item = Modchu_Main.newModchuCharacteristicObject("Modchu_Item", ItemRidingIndicatingRod.class, ridingIndicatingRodID);		if (debug) Modchu_Debug.mDebug("modc_RidingIndicatingRod load() item="+item);		item = Modchu_AS.get(Modchu_AS.itemSetUnlocalizedName, item, itemName);		if (debug) Modchu_Debug.mDebug("modc_RidingIndicatingRod load() itemSetUnlocalizedName item="+item);		item = Modchu_AS.get(Modchu_AS.itemSetCreativeTab, item, Modchu_AS.get(Modchu_AS.creativeTabsTabMaterials));		if (debug) Modchu_Debug.mDebug("modc_RidingIndicatingRod load() itemSetCreativeTab item="+item);		item = Modchu_AS.get(Modchu_AS.itemSetTextureName, item, itemName);		if (debug) Modchu_Debug.mDebug("modc_RidingIndicatingRod load() itemSetTextureName item="+item);		Modchu_Main.registerItem(item, itemName.toLowerCase());		itemInstance = (Modchu_IItem) item;		Modchu_Main.languageRegistryAddName(item, itemName.toLowerCase());		Modchu_Main.languageRegistryAddName(item, "ja_JP", "騎乗指示棒");		if (debug) Modchu_Debug.mDebug("modc_RidingIndicatingRod load() itemRidingIndicatingRod="+itemInstance);		//if (debug) Modchu_Debug.mDebug("modc_RidingIndicatingRod load() blaze_rod="+Modchu_AS.get(Modchu_AS.getItem, "blaze_rod"));		//if (debug) Modchu_Debug.mDebug("modc_RidingIndicatingRod load() redstone="+Modchu_AS.get(Modchu_AS.getItem, "redstone"));		Modchu_Main.addRecipe(Modchu_Main.newResourceLocation(itemName.toLowerCase()), Modchu_Reflect.newInstance("ItemStack", new Class[]{ Modchu_Reflect.loadClass("Item"), int.class }, new Object[]{ item, 1 }),				new Object[] { " Y ", "YXY", " Y ", Character.valueOf('X'),			Modchu_AS.get(Modchu_AS.getItem, "blaze_rod"), Character.valueOf('Y'), Modchu_AS.get(Modchu_AS.getItem, "redstone") });		//Object o = cpw.mods.fml.common.registry.GameRegistry.findItem("mod_Modchu_ModchuLib", itemName);		//if (debug) Modchu_Debug.mDebug("modc_RidingIndicatingRod load() o="+o);/*		if (debug) {			Map<String, Object> map = Modchu_Reflect.getAllFieldObject(item);			for (Entry<String, Object> en : map.entrySet()) {				String key = en.getKey();				Object o = en.getValue();				Modchu_Debug.mDebug("modc_RidingIndicatingRod load() getAllFieldObject key="+key+" o="+o);			}		}*/		//if (debug) Modchu_Debug.mDebug("modc_RidingIndicatingRod load() e ="+itemRidingIndicatingRod.itemsList[256 + ridingIndicatingRodID]);		if (debug) Modchu_Debug.mDebug("modc_RidingIndicatingRod load() end.");	}	public boolean modEnabled() {		return true;	}	public void init(Object event) {		//Modchu_Debug.lDebug("modc_RidingIndicatingRod init");		Modchu_Main.itemModelMesherRegister(itemInstance, 0, "modchulib:"+itemName.toLowerCase(), "inventory");		//Modchu_Debug.lDebug("modc_RidingIndicatingRod init end.");	}	public static void loadcfg() {		// cfg読み込み		if (Modchu_Main.cfgdir.exists()) {			ArrayList list = new ArrayList();			if (!mainCfgfile.exists()) {				// cfgファイルが無い = 新規作成				String s[] = {						"useAddChatMessage=true", "useOriginalIcon=true"				};				if (Modchu_Main.getMinecraftVersion() < 170) list.add("ridingIndicatingRodID=17660");				for(String s1 : s) {					list.add(s1);				}				Modchu_Config.writerConfig(mainCfgfile, list);			} else {				// cfgファイルがある				ConcurrentHashMap<String, String> map = Modchu_Config.loadAllConfig(mainCfgfile);				useAddChatMessage = map.containsKey("useAddChatMessage") ? Modchu_CastHelper.Boolean(map.get("useAddChatMessage")) : useAddChatMessage;				useOriginalIcon = map.containsKey("useOriginalIcon") ? Modchu_CastHelper.Boolean(map.get("useOriginalIcon")) : useOriginalIcon;				if (Modchu_Main.getMinecraftVersion() < 170) ridingIndicatingRodID = map.containsKey("ridingIndicatingRodID") ? Modchu_CastHelper.Int(map.get("ridingIndicatingRodID")) : ridingIndicatingRodID;				String k[] = {						"useAddChatMessage", "useOriginalIcon"				};				String k1[] = {						""+useAddChatMessage, ""+useOriginalIcon				};				ArrayList list2 = new ArrayList();				if (Modchu_Main.getMinecraftVersion() < 170) {					list.add("ridingIndicatingRodID");					list2.add(""+ridingIndicatingRodID);				}				for(String s1 : k) {					list.add(s1);				}				for(String s1 : k1) {					list2.add(s1);				}				Modchu_Config.writerSupplementConfig(mainCfgfile, list, list2);			}		}	}}