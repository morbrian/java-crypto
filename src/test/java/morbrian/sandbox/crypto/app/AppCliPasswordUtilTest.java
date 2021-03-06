package morbrian.sandbox.crypto.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;

@EnableRuleMigrationSupport
public class AppCliPasswordUtilTest {

  private TemporaryFolder tempFolder = new TemporaryFolder();

  @BeforeAll
  public void setup() throws Exception {
    tempFolder.create();
    AppCliPasswordUtil.NEVER_SYSTEM_EXIT = true;
  }

  @Test
  public void runStdInPasswordSpecifyFileTest() throws Exception {
    InputStream systemIn = System.in;
    PrintStream systemOut = System.out;
    try {
      // specify output file parameter
      File outputFile = tempFolder.newFile();
      String[] args = {"-F" + outputFile.getAbsolutePath()};

      // setup to get password from pseudo-stdin
      InputStream stringStream = new ByteArrayInputStream("sampltext".getBytes());
      System.setIn(stringStream);

      // run the app
      AppCliPasswordUtil.main(args);

      // verify output file exists and has expected properties
      Config config = ConfigFactory.parseFile(outputFile);
      assertNotNull(config.getString("value"));
      assertNotNull(config.getString("key"));
      assertTrue(config.getBoolean("encrypted"));
      assertEquals(128, config.getInt("keySize"));

    } finally {
      System.setIn(systemIn);
      System.setOut(systemOut);
    }
  }
}
