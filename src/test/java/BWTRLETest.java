import org.junit.Test;
import static org.junit.Assert.*;

import compressor.BWTRLE;

import config.Config;


public class BWTRLETest {


    @Test 
    public void encodingHappensTest() {
        BWTRLE bwtrle = new BWTRLE(new Config());
        String s = "Is this the real life?";
        byte[] encoded = bwtrle.encode(s);

        assertTrue(encoded.length > 0);
    }

    @Test
    public void encodedIsShorterThanOriginal() {
        BWTRLE bwtrle = new BWTRLE(new Config());
        String s = "aaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbb";
        byte[] encoded = bwtrle.encode(s);
        String decoded = bwtrle.decode(encoded);

        assertTrue(decoded.equals(s));
        assertTrue(s.length() > encoded.length);
    }
    
    @Test
    public void encodeDecodeMatchesOriginalInputTest() {
        BWTRLE bwtrle = new BWTRLE(new Config());
        String s = "Is this just fantasy? Caught in a land slide. No escape from reality.";
        byte[] encoded = bwtrle.encode(s);
        String decoded = bwtrle.decode(encoded);
        
        assertEquals(decoded, s);
    }
 
    @Test
    public void chunkedEncodeDecodeWorks() {
        Config config = new Config();
        BWTRLE bwtrle = new BWTRLE(config);
        String s = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas eget mi in mi vulputate dignissim. Quisque eros velit, elementum non odio non, iaculis scelerisque diam. Sed ac felis id libero cursus auctor. Donec tristique facilisis porttitor. Vestibulum dapibus augue non diam tempus mollis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Fusce maximus porta nunc, at viverra nibh dignissim vel." +
            "Nulla in magna id massa vulputate fringilla eu ac eros. Pellentesque malesuada elementum enim, at pulvinar quam dignissim in. Cras ullamcorper nisl ac ornare commodo. Nunc dignissim molestie odio, tincidunt commodo neque venenatis id. Mauris blandit lectus vel laoreet varius. Morbi rhoncus tortor eget nulla sollicitudin aliquam. Morbi id gravida nunc. Nunc quis malesuada velit. In in aliquam massa. Integer enim nibh, luctus vitae ornare nec, semper eget risus. Nullam maximus nisi id eros varius aliquam. Morbi ornare libero in dui viverra, tincidunt rutrum nibh molestie. Nunc rutrum libero ex, vulputate lacinia nisl faucibus sagittis." +
            "In interdum volutpat tempus. Morbi hendrerit est nec ex elementum, id elementum turpis lobortis. Nulla mollis gravida dui, eu sagittis magna rhoncus at. In maximus sodales mattis. Vivamus vel dolor nec nibh tempus ultrices. Proin at viverra est, vel aliquam ex. Praesent suscipit, purus a varius iaculis, sapien ante tristique massa, et placerat arcu ante nec sem. Curabitur aliquet facilisis congue. Cras sit amet rutrum sapien, nec iaculis erat. Sed odio eros, dapibus vitae turpis mollis, placerat aliquam purus. Praesent at commodo leo. Donec nec tempus purus, sit amet porta felis. Vivamus tempus vehicula lacus id hendrerit. Vestibulum blandit, nisl ut tempor euismod, sapien ligula molestie arcu, sed commodo quam nisl sit amet nisl. Vestibulum turpis tellus, auctor at libero in, feugiat lobortis lorem." +
            "Pellentesque tempus sed magna ac scelerisque. Nullam eu erat ornare nibh dapibus volutpat. Mauris ac felis sollicitudin, molestie sem nec, luctus mauris. Mauris nec quam nec ante maximus placerat eget at leo. Ut iaculis congue egestas. Maecenas volutpat bibendum nulla viverra bibendum. Aenean maximus justo in odio lacinia viverra. Aliquam in augue vel dui ultrices pharetra in nec urna. Maecenas tincidunt diam quis diam ultrices, ut varius ante aliquam. Cras eget pulvinar urna. Praesent libero est, luctus id ultrices vitae, molestie vitae neque. Maecenas sit amet purus nunc. Nam non augue dictum, porttitor quam in, suscipit velit. Vestibulum leo mauris, condimentum quis euismod ac, tristique vel urna. Aenean commodo quam velit, non malesuada odio condimentum ac." +
            "In sed sollicitudin orci. Quisque ut maximus libero. Quisque eleifend, mauris at laoreet pharetra, lectus tellus egestas mauris, et venenatis ipsum elit at erat. Donec pulvinar volutpat urna, quis tincidunt mauris mollis vitae. Praesent dui lorem, ultricies in lacinia pharetra, suscipit et ipsum. Vivamus aliquam, lacus a pharetra rhoncus, lacus augue gravida ligula, eu dignissim eros est sit amet libero. Donec pharetra metus ut sapien hendrerit, in posuere eros tincidunt. Vivamus tempor in neque at sodales. Aliquam id mi est. Quisque a elit at nulla rutrum ultricies non in orci. Interdum et malesuada fames ac ante ipsum primis in faucibus. Aliquam at sodales ipsum. Sed neque ligula, rutrum ac erat pretium, pretium maximus lacus. Nullam ultricies vehicula pellentesque." +
            "Nunc erat magna, ullamcorper id sodales in, fringilla quis magna. Aliquam ullamcorper laoreet sollicitudin. Fusce purus justo, consectetur eu nisl aliquam, lacinia ullamcorper augue. Pellentesque nec efficitur neque. Donec in pharetra eros, sit amet rutrum nibh. Curabitur eu sapien at ligula dignissim facilisis vitae nec dolor. Aenean tempor elit in pulvinar malesuada. Duis porttitor congue ullamcorper." +
            "Aliquam velit ligula, varius et diam at, commodo luctus erat. Duis non augue lobortis, tincidunt felis in, interdum justo. Sed cursus, dolor nec faucibus consequat, eros arcu molestie augue, ac ultricies nulla arcu quis orci. Morbi a enim vitae eros efficitur bibendum. Fusce tristique vitae nisl ut interdum. Praesent quam neque, porttitor nec pretium tincidunt, fringilla eu magna. Maecenas eget mattis lectus. Sed congue sem eget dui pulvinar dignissim. Nulla imperdiet, quam id tristique tempor, nisl eros pulvinar elit, nec feugiat felis dui a turpis." +
            "Morbi fringilla vitae tellus id faucibus. Vivamus tincidunt sem in magna laoreet dignissim. Cras ullamcorper nunc metus, quis consectetur nibh rhoncus nec. Quisque lobortis vestibulum magna, ac efficitur lorem. Nullam sed nisl tempor, porta erat eu, imperdiet massa. Nullam nec quam imperdiet, vehicula ex sed, mollis velit. Suspendisse placerat, nisi et ultricies pulvinar, lorem nisl commodo massa, a egestas massa lacus eget arcu. Aliquam elementum id erat sed pretium. Interdum et malesuada fames ac ante ipsum primis in faucibus." +
            "Vestibulum feugiat sit amet odio eget porttitor. In hac habitasse platea dictumst. Sed faucibus velit et mattis vestibulum. Aliquam vel est vitae arcu convallis convallis vitae a nunc. Donec sollicitudin dictum enim. Nam iaculis dui ante, eu consectetur tellus tincidunt finibus. Donec maximus commodo felis, ac pellentesque lorem interdum in. Nulla facilisi. Maecenas sodales enim dui, eu sodales lectus eleifend sed. Maecenas nec purus maximus, faucibus quam a, tristique lacus. Aenean a gravida lorem, ultricies blandit augue." +
            "Donec sed enim ullamcorper, convallis augue sit amet, tempor turpis. Praesent efficitur malesuada magna, eu efficitur mauris fringilla in. Duis sit amet accumsan libero. Vivamus ac mi cursus, tristique arcu sed, fringilla enim. Vestibulum sodales vitae turpis porta suscipit. Nulla facilisi. Curabitur eleifend lectus vel rhoncus ultricies. Sed ac consequat ex, vel porta nulla. Integer sed odio et est tristique blandit eu ut nisi. In dapibus tellus vel libero finibus, ac maximus mi ultricies. Vestibulum non nisi nulla. Sed in est mauris. Duis non dui egestas, gravida libero vel, suscipit erat. Ut sed risus eget diam commodo aliquam a at diam. Ut dui sem, accumsan eu purus eu, placerat imperdiet lorem." +
            "Pellentesque pellentesque leo lectus, id efficitur eros efficitur eget. Aliquam posuere lorem ut turpis commodo, sit amet pretium eros placerat. Sed in lorem eget felis maximus vestibulum vitae id nibh. Vivamus commodo facilisis pulvinar. Integer non iaculis sem, nec eleifend massa. Vivamus at tincidunt diam, quis lobortis nisl. Cras a tincidunt lectus, a vulputate velit. Donec nec vehicula risus. Aliquam in tincidunt mi." +
            "Sed eu congue lorem. Suspendisse id massa vel turpis congue condimentum. Aliquam diam lectus, fringilla quis mauris a, volutpat commodo tellus. Nunc molestie rhoncus diam et finibus. Integer accumsan velit id sem consequat semper. Phasellus id lorem vitae justo dictum porta eu rutrum urna. Duis ut nisl elit. Nulla lacinia justo nec luctus dignissim. Cras turpis tortor, viverra vitae interdum non, vestibulum vel orci. Fusce eget dui pharetra, porta purus sed, dapibus enim. Cras nec porttitor neque. Curabitur suscipit augue dui, vel vestibulum eros vestibulum sed. Maecenas vel lectus at eros semper efficitur ut eu enim. Pellentesque ac nisl cursus, pellentesque odio tristique, fringilla turpis. Quisque euismod justo in viverra pretium." +
            "Proin eget magna ac sapien dapibus mollis. Sed mollis eleifend ante, vitae tempor velit iaculis sit amet. Donec ornare massa massa, et faucibus ex cursus eget. Proin blandit, leo ut accumsan facilisis, elit tellus tristique dolor, sed dignissim mauris tortor vel enim. Quisque elementum ultricies felis, nec sollicitudin justo iaculis eu. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Sed dictum lacus fringilla neque lacinia, in dictum lacus convallis. Vivamus laoreet lorem dignissim lorem euismod aliquam. Praesent felis est, lobortis eu ligula vel, blandit auctor erat. Fusce at est nec nisl tincidunt fermentum." +
            "Fusce scelerisque hendrerit mauris, vitae feugiat nulla aliquam vel. Nullam pretium lobortis metus, vel vehicula eros ornare vel. Aliquam id nibh nisl. Mauris non pharetra enim non."
        ;
        byte[] encoded = bwtrle.encode(s);
        String decoded = bwtrle.decode(encoded);
        System.out.println(decoded);
        
        assertTrue(decoded.equals(s));
    }
}