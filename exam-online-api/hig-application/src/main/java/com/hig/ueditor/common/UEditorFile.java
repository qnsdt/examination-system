package com.hig.ueditor.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UEditorFile {

    private static final long serialVersionUID=1L;

    private String state;

    private String url;

    private String title;

    private String original;

    @Override
    public String toString() {
        return "{" +
                "state='" + state + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", original='" + original + '\'' +
                '}';
    }
}

