package net.minecraft.src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class mod_RidingIndicatingRod extends BaseMod {

	public static int ridingIndicatingRodID = 17660;
	public static boolean useAddChatMessage = true;
	public static boolean useOriginalIcon = true;

	public static Item ridingIndicatingRod;
	private static boolean DebugMessage = true;
	private static mod_RidingIndicatingRod mod_ridingIndicatingRod;
	private static final File cfgdir = new File(Minecraft.getMinecraft().mcDataDir, "/config/");
	private static File whiteListfile = new File(cfgdir, ("RidingIndicatingRod_whiteList.cfg"));
	private static File ngListfile = new File(cfgdir, ("RidingIndicatingRod_NGList.cfg"));
	public static List<String> whiteList = new ArrayList<String>();
	public static List<String> ngList = new ArrayList<String>();
	private static File mainCfgfile = new File(cfgdir, ("RidingIndicatingRod.cfg"));
	public static String itemName;
	private String packageName;

	public static void Debug(String s)
	{
		if (DebugMessage)
		{
			System.out.println((new StringBuilder()).append("ridingIndicatingRod-").append(s).toString());
		}
	}

	public static void mDebug(String s)
	{
		if (DebugMessage
				&& !mod_ridingIndicatingRod.isRelease())
		{
			System.out.println((new StringBuilder()).append("ridingIndicatingRod_").append(s).toString());
		}
	}

	public boolean isRelease() {
		return getPackage() == null;
	}

	public String getVersion() {
		return "1.6.1-1c";
	}

	public void load() {
		mod_ridingIndicatingRod = this;
		loadcfg();
		itemName = "RidingIndicatingRod";
		ridingIndicatingRod = new ItemRidingIndicatingRod(
				ridingIndicatingRodID - 256).setUnlocalizedName(
				itemName).setCreativeTab(CreativeTabs.tabMaterials);
		ModLoader.addName(ridingIndicatingRod, itemName);
		ModLoader.addRecipe(new ItemStack(ridingIndicatingRod, 1),
				new Object[] { " Y ", "YXY", " Y ", Character.valueOf('X'),
						Item.blazeRod, Character.valueOf('Y'), Item.redstone });
		//loadList();
	}

	public static void writerList(String[] s, File file, List<String> list) {
		//Listファイル書き込み
		try {
			BufferedWriter bwriter = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < s.length ; i++)
			{
				//mDebug("s[i]="+s[i]);
				if (s[i] != null) {
					bwriter.write(s[i]);
					list.add(s[i]);
					bwriter.newLine();
				}
			}
			bwriter.close();
			Debug("file new file create.");
		} catch (Exception e) {
			Debug("file writer fail.");
			e.printStackTrace();
			Debug(" ");
		}
	}
/*
	public static void loadList() {
		// List読み込み
		if (cfgdir.exists()) {
			if (!whiteListfile.exists()) {
				// whiteListファイルが無い = 新規作成
				String s[] = {
						"EntityCreature", "aaa", "ozelot", "wolf", "cow",
						"chicken", "pig", "sheep", "golem", "squid",
						"villager"
				};
				writerList(s, whiteListfile, whiteList);
			} else {
				// whiteListファイルがある
				try {
					BufferedReader breader = new BufferedReader(new FileReader(
							whiteListfile));
					String rl;
					int i = 0;
					while ((rl = breader.readLine()) != null) {
						whiteList.add(rl);
						i++;
					}
					breader.close();
					mDebug("whiteList load end.");
				} catch (Exception e) {
					Debug("whiteList file load fail.");
		            e.printStackTrace();
					Debug(" ");
				}
			}
			if (!ngListfile.exists()) {
				// NGListファイルが無い = 新規作成
				String s[] = {
						"EntityMob", "yy"
				};
				writerList(s, ngListfile, ngList);
			} else {
				// NGListファイルがある
				try {
					BufferedReader breader = new BufferedReader(new FileReader(
							ngListfile));
					String rl;
					int i = 0;
					while ((rl = breader.readLine()) != null) {
						ngList.add(rl);
						i++;
					}
					breader.close();
					mDebug("NGList load end.");
				} catch (Exception e) {
					Debug("NGList file load fail.");
		            e.printStackTrace();
					Debug(" ");
				}
			}
		}
	}
*/
	public static void loadcfg() {
		// cfg読み込み
		if (cfgdir.exists()) {
			if (!mainCfgfile.exists()) {
				// cfgファイルが無い = 新規作成
				String s[] = {
						"ridingIndicatingRodID=17660", "useAddChatMessage=true", "useOriginalIcon=true"
				};
				RidingIndicatingRodConfig.writerConfig(mainCfgfile, s);
			} else {
				// cfgファイルがある
				ridingIndicatingRodID = Integer.valueOf((RidingIndicatingRodConfig.loadConfig(mainCfgfile, "ridingIndicatingRodID", ridingIndicatingRodID)).toString());
				useAddChatMessage = Boolean.valueOf((RidingIndicatingRodConfig.loadConfig(mainCfgfile, "useAddChatMessage", useAddChatMessage)).toString());
				useOriginalIcon = Boolean.valueOf((RidingIndicatingRodConfig.loadConfig(mainCfgfile, "useOriginalIcon", useOriginalIcon)).toString());
				cfgMaxMinCheck();
				String k[] = {
						"ridingIndicatingRodID", "useAddChatMessage", "useOriginalIcon"
				};
				String k1[] = {
						""+ridingIndicatingRodID,""+useAddChatMessage, ""+useOriginalIcon
				};
				RidingIndicatingRodConfig.writerSupplementConfig(mainCfgfile, k, k1);
			}
		}
	}

	public static void cfgMaxMinCheck() {
		if (ridingIndicatingRodID < 0) ridingIndicatingRodID = 0;
		if (ridingIndicatingRodID > 32000) ridingIndicatingRodID = 32000;
	}

	public String getPackage() {
		if (packageName != null) return packageName;
		try {
			String s = ""+Class.forName("net.minecraft.src.FMLRenderAccessLibrary");
			Class c = Class.forName("net.minecraft.src.mod_RidingIndicatingRod");
			if (c != null) return packageName = "net.minecraft.src";
			return packageName;
		} catch (Exception e) {
		}
		Package pac = getClass().getPackage();
		if (pac != null) {
			packageName = pac.getName();
			return packageName;
		}
		return packageName;
	}
}