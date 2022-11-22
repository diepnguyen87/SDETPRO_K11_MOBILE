package src.test_data.utils;

import com.google.gson.Gson;
import net.bytebuddy.implementation.bytecode.Throw;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class DataObjectBuilder {

    public static <T> T buildDataObject(String fileLocation, Class<T> datatype) {
        T returnData = null;
        String absoluteFilePath = System.getProperty("user.dir").concat(fileLocation);
        try (
                Reader reader = Files.newBufferedReader(Paths.get(absoluteFilePath));
        ) {
            Gson gson = new Gson();
            returnData = gson.fromJson(reader, datatype);

        } catch (NoSuchFileException nsfe) {
            throw new RuntimeException("[ERR] Could not locate the found " + absoluteFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(returnData == null){
            throw new RuntimeException("[ERR] returned data is null");
        }
        return returnData;
    }
}
