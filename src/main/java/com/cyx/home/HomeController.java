package com.cyx.home;

import com.github.chengyuxing.common.DataRow;
import com.github.chengyuxing.sql.Baki;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    final Baki baki;
    final Baki baki2;

    public HomeController(Baki baki, @Qualifier("otherBaki") Baki baki2) {
        this.baki = baki;
        this.baki2 = baki2;
    }

    @GetMapping("")
    String index() {
        return "Hello world!";
    }

    @GetMapping("/query")
    DataRow query() {
        String a = baki.fetch("select now()").orElse(DataRow.empty()).getString(0);
        String b = baki2.fetch("select true").orElse(DataRow.empty()).getString(0);
        return DataRow.fromPair("a", a, "b", b);
    }
}
