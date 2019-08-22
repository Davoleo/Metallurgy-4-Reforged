import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class Transformer {

	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, IOException {
		transformBlockStates();
		//transformLang();
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

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            JsonObject lang = new JsonObject();
            
            prop.keySet().stream().sorted().forEach(key -> {
            	
            	lang.addProperty((String) key, prop.getProperty((String) key));
            	
            });
            
            Gson gson = new GsonBuilder()
            		  .setPrettyPrinting()
            		  .create();
            
            BufferedWriter bufwriter = new BufferedWriter((new FileWriter("src/main/resources/assets/metallurgy/lang/en_us.json")));
            bufwriter.write(gson.toJson(lang));//writes the edited string buffer to the new file
            bufwriter.close();
            
            System.out.println(lang.toString());
            
            System.out.println(prop.size());
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}

}
