package utils.files;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.web.multipart.MultipartFile;
import ru.voronasever.voronaStore.dto.ProductCsvDto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CsvFileParser {


    public static <T> List<T> loadObjectListFromCsv(Class<T> objectClass, MultipartFile multipartFile) throws IOException {
        File file = multipartFileToFile(multipartFile);
           try{
               CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
               CsvMapper csvMapper = new CsvMapper();
               MappingIterator<T> objectMappingIterator = csvMapper.reader().forType(objectClass).with(csvSchema).readValues(file);
               return objectMappingIterator.readAll();
           }finally{
               multipartFile.getInputStream().close();
               Files.delete(Path.of(file.getAbsolutePath()));
           }

    }


    private static File multipartFileToFile(MultipartFile multipartFile) throws IOException {
        String path = "src\\main\\resources\\files\\csv\\" + multipartFile.getOriginalFilename();
        Path filepath = Paths.get(path);
        multipartFile.transferTo(filepath);
        multipartFile.getInputStream().close();
        return new File(path);


    }
}
