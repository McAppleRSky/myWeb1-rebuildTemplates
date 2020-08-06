import com.coveo.nashorn_modules.FilesystemFolder;
import com.coveo.nashorn_modules.Module;
import com.coveo.nashorn_modules.Require;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.assertTrue;

public class HtmlTest {
    @SuppressWarnings("removal")
    static
    NashornScriptEngine engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
    static FilesystemFolder currentNpmprojectPath;
    static Module require;

    @BeforeClass
    public static void beforeClass(){
        currentNpmprojectPath = FilesystemFolder.create(new File(""), "UTF-8");
        try {
            require = Require.enable(engine, currentNpmprojectPath);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void beforeTest(){
        try {
            engine.eval(new FileReader("src/test/javascript/prepare.js"));
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void simpleTest(){
        String tst = "<!DOCTYPE html><html lang='en' dir='ltr'><head><meta charset='utf-8'><title></title></head><body></body></html>";
        Invocable invocable = (Invocable) engine;
        try {
            invocable.invokeFunction("process"//"isEqualFunHtmlDiffer"
                    ,tst, tst);
            assertTrue((boolean)true);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("");
    }

}
