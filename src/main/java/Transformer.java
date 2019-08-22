import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import it.hurts.metallurgy_reforged.util.Utils;

public class Transformer {

	public static String[] modMaterialNames = {
			"adamantine",
			"amordrine",
			"angmallen",
			"astral_silver",
			"atlarus",
			"black_steel",
			"brass",
			"bronze",
			"carmot",
			"celenegil",
			"ceruclase",
			"copper",
			"damascus_steel",
			"deep_iron",
			"desichalkos",
			"electrum",
			"eximite",
			"haderoth",
			"hepatizon",
			"ignatius",
			"inolashite",
			"kalendrite",
			"krik",
			"lutetium",
			"midasium",
			"mithril",
			"orichalcum",
			"osmium",
			"oureclase",
			"platinum",
			"prometheum",
			"quicksilver",
			"sanguinite",
			"shadow_iron",
			"shadow_steel",
			"silver",
			"steel",
			"tartarite",
			"vulcanite",
			"vyroxeres",
		};
	
	public static String[] modOres = {
			"potash",
			"sulfur",
			"phosphorite",
			"tar",
			"manganese",
			"zinc",
			"prometheum",
			"deep_iron",
			"infuscolium",
			"manganese",
			"oureclase",
			"astral_silver",
			"carmot",
			"rubracium",
			"osmium",
			"lutetium",
			"orichalcum",
			"adamantine",
			"atlarus",
			"ignatius",
			"shadowIron",
			"lemurite",
			"alduorite",
			"ceruclase",
			"midasium",
			"vulcanite",
			"sanguinite",
			"eximite",
			"meutoite"
	};
	
	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, IOException {
		//transformBlockStates();
		//transformLang();
		//addOreDictionary();
		addLootTables();
	}
	
	private static void addLootTables() throws IOException {
		Gson gson = new GsonBuilder()
	      		  .setPrettyPrinting()
	      		  .create();
		
		for (String metal : modMaterialNames) {

			JsonObject entry = new JsonObject();
			entry.addProperty("type", "minecraft:item");
			entry.addProperty("name", "metallurgy:" + metal + "_block");
			
			JsonObject condition = new JsonObject();
			condition.addProperty("condition", "minecraft:survives_explosion");
			
			JsonObject pool = new JsonObject();
			pool.addProperty("name", "pool1");
			pool.addProperty("rolls", 1);
			pool.add("entries", new JsonArray());
			pool.getAsJsonArray("entries").add(entry);
			pool.add("conditions", new JsonArray());
			pool.getAsJsonArray("conditions").add(condition);
			
			JsonObject json = new JsonObject();
			json.addProperty("type", "minecraft:block");
			json.add("pools", new JsonArray());
			json.getAsJsonArray("pools").add(pool);
			
			BufferedWriter bufwriter = new BufferedWriter(new FileWriter(new File("src/main/resources/data/metallurgy/loot_tables/blocks", metal + "_block.json")));
            bufwriter.write(gson.toJson(json));//writes the edited string buffer to the new file
            bufwriter.close();
            
            if (Arrays.stream(modOres).anyMatch(ore -> ore.equalsIgnoreCase(metal))) {
            	entry = new JsonObject();
    			entry.addProperty("type", "minecraft:item");
    			entry.addProperty("name", "metallurgy:" + metal + "_ore");
            	
    			json.getAsJsonArray("pools").get(0).getAsJsonObject().getAsJsonArray("entries").remove(0);
    			json.getAsJsonArray("pools").get(0).getAsJsonObject().getAsJsonArray("entries").add(entry);
    			
            	bufwriter = new BufferedWriter(new FileWriter(new File("src/main/resources/data/metallurgy/loot_tables/blocks", metal + "_ore.json")));
                bufwriter.write(gson.toJson(json));//writes the edited string buffer to the new file
                bufwriter.close();
            }
		}
	}

	public static void addOreDictionary() throws IOException {
		Gson gson = new GsonBuilder()
	      		  .setPrettyPrinting()
	      		  .create();
		
		JsonArray blockArray = new JsonArray();
		JsonArray oreArray = new JsonArray();
		JsonArray dustArray = new JsonArray();
		JsonArray ingotArray = new JsonArray();
		JsonArray nuggetArray = new JsonArray();
		
		for (String metal : modMaterialNames) {
			blockArray.add("#forge:storage_blocks/" + metal);
			dustArray.add("#forge:dusts/" + metal);
			ingotArray.add("#forge:ingots/" + metal);
			nuggetArray.add("#forge:nuggets/" + metal);
			
			JsonArray values = new JsonArray();
			values.add("metallurgy:" + metal + "_block");
			
			JsonObject json = new JsonObject();
			json.addProperty("replace", false);
			json.add("values", values);
            
			BufferedWriter bufwriter = new BufferedWriter(new FileWriter(new File("src/main/resources/data/forge/tags/blocks/storage_blocks", metal + ".json")));
            bufwriter.write(gson.toJson(json));//writes the edited string buffer to the new file
            bufwriter.close();
            
            if (Arrays.stream(modOres).anyMatch(ore -> ore.equalsIgnoreCase(metal))) {
            	oreArray.add("#forge:ores/" + metal);
            	
				values = new JsonArray();
				values.add("metallurgy:" + metal + "_ore");
				
				json = new JsonObject();
				json.addProperty("replace", false);
				json.add("values", values);
				
				bufwriter = new BufferedWriter(new FileWriter(new File("src/main/resources/data/forge/tags/blocks/ores", metal + ".json")));
	            bufwriter.write(gson.toJson(json));//writes the edited string buffer to the new file
	            bufwriter.close();
            }
			
            values = new JsonArray();
			values.add("metallurgy:" + metal + "_dust");
			
			json = new JsonObject();
			json.addProperty("replace", false);
			json.add("values", values);
            
            bufwriter = new BufferedWriter(new FileWriter(new File("src/main/resources/data/forge/tags/items/dusts", metal + ".json")));
            bufwriter.write(gson.toJson(json));//writes the edited string buffer to the new file
            bufwriter.close();
            
            values = new JsonArray();
			values.add("metallurgy:" + metal + "_ingot");
			
			json = new JsonObject();
			json.addProperty("replace", false);
			json.add("values", values);
            
            bufwriter = new BufferedWriter(new FileWriter(new File("src/main/resources/data/forge/tags/items/ingots", metal + ".json")));
            bufwriter.write(gson.toJson(json));//writes the edited string buffer to the new file
            bufwriter.close();
            
            values = new JsonArray();
			values.add("metallurgy:" + metal + "_nugget");
			
			json = new JsonObject();
			json.addProperty("replace", false);
			json.add("values", values);
            
            bufwriter = new BufferedWriter(new FileWriter(new File("src/main/resources/data/forge/tags/items/nuggets", metal + ".json")));
            bufwriter.write(gson.toJson(json));//writes the edited string buffer to the new file
            bufwriter.close();
		}
		
		JsonObject json = new JsonObject();
		json.addProperty("replace", false);
		json.add("values", blockArray);
		
		BufferedWriter bufwriter = new BufferedWriter(new FileWriter(new File("src/main/resources/data/forge/tags/blocks/storage_blocks.json")));
        bufwriter.write(gson.toJson(json));//writes the edited string buffer to the new file
        bufwriter.close();
        
        json.remove("values");
        json.add("values", oreArray);
        bufwriter = new BufferedWriter(new FileWriter(new File("src/main/resources/data/forge/tags/blocks/ores.json")));
        bufwriter.write(gson.toJson(json));//writes the edited string buffer to the new file
        bufwriter.close();
        
        json.remove("values");
        json.add("values", dustArray);
        bufwriter = new BufferedWriter(new FileWriter(new File("src/main/resources/data/forge/tags/items/dusts.json")));
        bufwriter.write(gson.toJson(json));//writes the edited string buffer to the new file
        bufwriter.close();
        
        json.remove("values");
        json.add("values", ingotArray);
        bufwriter = new BufferedWriter(new FileWriter(new File("src/main/resources/data/forge/tags/items/ingots.json")));
        bufwriter.write(gson.toJson(json));//writes the edited string buffer to the new file
        bufwriter.close();
        
        json.remove("values");
        json.add("values", nuggetArray);
        bufwriter = new BufferedWriter(new FileWriter(new File("src/main/resources/data/forge/tags/items/nuggets.json")));
        bufwriter.write(gson.toJson(json));//writes the edited string buffer to the new file
        bufwriter.close();
	}

	public static void transformBlockStates() throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new GsonBuilder()
      		  .setPrettyPrinting()
      		  .create();
		
		//File newFile = new File("src/main/resources/assets/metallurgy/temp");
		//if (!newFile.exists()) newFile.mkdir();
		
		File blockDir = new File("src/main/resources/assets/metallurgy/models/block");
		File itemDir = new File("src/main/resources/assets/metallurgy/models/item");
		
		Iterator<File> it = FileUtils.iterateFiles(new File("src/main/resources/assets/metallurgy/blockstates"), null, false);
		
		while (it.hasNext()) {
			File file = it.next();
			
			Reader read = new FileReader(file);
			JsonObject json = gson.fromJson(new FileReader(file), JsonObject.class);
			read.close();
			
			if (!json.has("forge_marker") || file.getName().contains("molten")) continue;
			
			String texture = json.getAsJsonObject("defaults").getAsJsonObject("textures").get("all").getAsString();
			
			JsonObject model = new JsonObject();
			model.addProperty("model", texture.replaceFirst("blocks", "block"));
			
			JsonObject variants = new JsonObject();
			variants.add("", model);
			
			JsonObject newJson = new JsonObject();
			newJson.add("variants", variants);
			
			BufferedWriter bufwriter = new BufferedWriter(new FileWriter(file));
            bufwriter.write(gson.toJson(newJson));//writes the edited string buffer to the new file
            bufwriter.close();
            
            JsonObject textures = new JsonObject();
            textures.addProperty("all", texture);
            
            JsonObject blockJson = new JsonObject();
            blockJson.addProperty("parent", "block/cube_all");
            blockJson.add("textures", textures);
            
            bufwriter = new BufferedWriter(new FileWriter(new File(blockDir, file.getName())));
            bufwriter.write(gson.toJson(blockJson));//writes the edited string buffer to the new file
            bufwriter.close();
            
            JsonObject itemJson = new JsonObject();
            itemJson.addProperty("parent", texture.replaceFirst("blocks", "block"));
            
            bufwriter = new BufferedWriter(new FileWriter(new File(itemDir, file.getName())));
            bufwriter.write(gson.toJson(itemJson));//writes the edited string buffer to the new file
            bufwriter.close();
		}
	}
	
	public static void transformLang() {
		try (InputStream input = new FileInputStream("src/main/resources/assets/metallurgy/lang/en_us.lang")) {
			JsonObject lang = new JsonObject();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			reader.lines().filter(line -> !line.startsWith("#") && !line.trim().isEmpty()).forEach(line -> {
				String name = line.substring(0, line.indexOf("="));
				String value = line.substring(line.indexOf("=") + 1);
				
				if (name.endsWith(".name")) name = name.substring(0, name.lastIndexOf("."));
				
				lang.addProperty(name, value);
			});
			/*
            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            
            
            prop.keySet().stream().sorted().forEach(key -> {
            	
            	lang.addProperty((String) key, prop.getProperty((String) key));
            	
            });
            */
            Gson gson = new GsonBuilder()
            		  .setPrettyPrinting()
            		  .create();
            
            BufferedWriter bufwriter = new BufferedWriter((new FileWriter("src/main/resources/assets/metallurgy/lang/en_us.json")));
            bufwriter.write(gson.toJson(lang));//writes the edited string buffer to the new file
            bufwriter.close();
            
            System.out.println(gson.toJson(lang));
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}

}
