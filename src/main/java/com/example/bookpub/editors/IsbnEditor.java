package com.example.bookpub.editors;

import static java.util.Objects.isNull;

import com.example.bookpub.model.Isbn;
import java.beans.PropertyEditorSupport;
import org.springframework.util.StringUtils;

public class IsbnEditor extends PropertyEditorSupport {

  @Override
  public String getAsText() {
    Object value = getValue();

    return (value != null ? value.toString() : "");
  }

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    if (isNull(text)) {
      setValue(null);
    } else {
      String value = text.trim();
      if (!StringUtils.isEmpty(value)) {
        setValue(Isbn.parseFrom(value));
      } else { setValue(null); }
    }
  }
}
