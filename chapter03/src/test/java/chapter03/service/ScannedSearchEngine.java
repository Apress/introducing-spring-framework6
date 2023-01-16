package chapter03.service;

import chapter03.StaticSearchEngine;
import org.springframework.stereotype.Service;

@Service
public class ScannedSearchEngine
  extends StaticSearchEngine {
  ScannedSearchEngine() {
    super(true);
  }
}
