package net.minecraft.src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;

public class mod_RidingIndicatingRod extends BaseMod {

	public static int ridingIndicatingRodID = 17660;
	public static boolean useAddChatMessage = true;

	public static Item ridingIndicatingRod;
	private static boolean DebugMessage = true;
	private static mod_RidingIndicatingRod mod_ridingIndicatingRod;
	private static final File cfgdir = new File(Minecraft.getMinecraftDir(), "/config/");
	private static File whiteListfile = new File(cfgdir, ("RidingIndicatingRod_whiteList.cfg"));
	private static File ngListfile = new File(cfgdir, ("RidingIndicatingRod_NGList.cfg"));
	public static List<String> whiteList = new ArrayList<String>();
	public static List<String> ngList = new ArrayList<String>();
	private static File mainCfgfile = new File(cfgdir, ("RidingIndicatingRod.cfg"));

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
		return getClass().getPackage() == null;
	}

	public String getVersion() {
		return "1.2.4~5-1a";
	}

	public void load() {
		mod_ridingIndicatingRod = this;
		loadcfg();
		ridingIndicatingRod = new ItemRidingIndicatingRod(
				ridingIndicatingRodID - 256).setIconCoord(12, 6).setItemName(
				"RidingIndicatingRod");
		ModLoader.addName(ridingIndicatingRod, "ridingIndicatingRod");
		ModLoader.addRecipe(new ItemStack(ridingIndicatingRod, 1),
				new Object[] { " Y ", "YXY", " Y ", Character.valueOf('X'),
						Item.blazeRod, Character.valueOf('Y'), Item.redstone });
		//loadList();
	}

	public static void writerList(String[] s, File file, List<String> list) {
		//List�t�@�C����������
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
		// List�ǂݍ���
		if (cfgdir.exists()) {
			if (!whiteListfile.exists()) {
				// whiteList�t�@�C�������� = �V�K�쐬
				String s[] = {
						"EntityCreature", "aaa", "ozelot", "wolf", "cow",
						"chicken", "pig", "sheep", "golem", "squid",
						"villager"
				};
				writerList(s, whiteListfile, whiteList);
			} else {
				// whiteList�t�@�C��������
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
				// NGList�t�@�C�������� = �V�K�쐬
				String s[] = {
						"EntityMob", "yy"
				};
				writerList(s, ngListfile, ngList);
			} else {
				// NGList�t�@�C��������
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
		// cfg�ǂݍ���
		if (cfgdir.exists()) {
			if (!mainCfgfile.exists()) {
				// cfg�t�@�C�������� = �V�K�쐬
				String s[] = {
						"ridingIndicatingRodID=17660","useAddChatMessage=true"
				};
				RidingIndicatingRodConfig.writerConfig(mainCfgfile, s);
			} else {
				// cfg�t�@�C��������
				ridingIndicatingRodID = Integer.valueOf((RidingIndicatingRodConfig.loadConfig(mainCfgfile, "ridingIndicatingRodID", ridingIndicatingRodID)).toString());
				useAddChatMessage = Boolean.valueOf((RidingIndicatingRodConfig.loadConfig(mainCfgfile, "useAddChatMessage", useAddChatMessage)).toString());
				cfgMaxMinCheck();
				String k[] = {
						"ridingIndicatingRodID","useAddChatMessage"
				};
				String k1[] = {
						""+ridingIndicatingRodID,""+useAddChatMessage
				};
				RidingIndicatingRodConfig.writerSupplementConfig(mainCfgfile, k, k1);
			}
		}
	}

	public static void cfgMaxMinCheck() {
		if (ridingIndicatingRodID < 0) ridingIndicatingRodID = 0;
		if (ridingIndicatingRodID > 32000) ridingIndicatingRodID = 32000;
	}
}