package pl.mirocha.marcin.it.book.store;

import org.apache.commons.codec.digest.DigestUtils;
import pl.mirocha.marcin.it.book.store.model.Book;

public class Test {
    public static void main(String[] args) {
//        String seed = "hq_6#%r8rPJ)zoZaIvV6X@HqMVaz;C7wNM39Rl]{hCE!YF]8Xo";
        String haslo = "janusz123";

        String hash = DigestUtils.md5Hex(haslo);
        System.out.println(hash);

    }
}
