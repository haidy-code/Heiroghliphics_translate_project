import 'package:flutter/material.dart';

class PlacesScreenModel with ChangeNotifier {
  bool isTheDataLoadedOk = false;
  List listOfData;//<Map<String , dynamic>>
  void dataLoaded(){
    isTheDataLoadedOk = true;
    notifyListeners();
  }
}