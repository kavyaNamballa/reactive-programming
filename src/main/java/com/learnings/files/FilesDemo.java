package com.learnings.files;

import com.learnings.common.Util;

public class FilesDemo {
    public static void main(String[] args) {
        var fileService = new FileServiceImpl();
        fileService.writeFile("file.txt", "This is a test file")
                .subscribe(Util.subscriber());

        fileService.readFile("file.txt")
                .subscribe(Util.subscriber());

//        fileService.deleteFile("file.txt")
//                .subscribe(Util.subscriber());

    }
}
