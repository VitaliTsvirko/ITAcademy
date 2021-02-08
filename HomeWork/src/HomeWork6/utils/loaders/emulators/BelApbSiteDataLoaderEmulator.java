package HomeWork6.utils.loaders.emulators;

import HomeWork6.utils.loaders.SiteDataLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BelApbSiteDataLoaderEmulator extends SiteDataLoader {

    @Override
    public String load(String urlToSite){
        try {
            return new String(Files.readAllBytes(Paths.get("d:\\01. Vitali\\03. Inf\\01. Java\\Lessons\\JD1\\HomeWork\\src\\HomeWork6\\utils\\test\\BelApbResponse.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
