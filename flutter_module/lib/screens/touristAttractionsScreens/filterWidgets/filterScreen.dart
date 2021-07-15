import 'package:flutter/material.dart';

class FilterScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return FilterScreenView();
  }
}

class FilterScreenView extends StatelessWidget {
  double screenWidth, screenHeight;
  @override
  Widget build(BuildContext context) {
    screenWidth = MediaQuery.of(context).size.width;
    screenHeight =
        MediaQuery.of(context).size.height - MediaQuery.of(context).padding.top;
    return Scaffold(
      body: Container(
        // padding: EdgeInsets.only(left: screenWidth * 0.05),
        child: Stack(
          children: [
            // page title
            Positioned(
                top: screenHeight * 0.1,
                child: Container(
                  padding: EdgeInsets.only(left: screenWidth * 0.05),
                  child: Text(
                    "Filter by :",
                    style: TextStyle(
                        fontSize: screenHeight * 0.04,
                        fontWeight: FontWeight.bold),
                  ),
                )),
            // the cross icon
            Positioned(
                top: screenHeight * 0.05,
                right: screenHeight * 0.05,
                child: IconButton(
                  icon: Icon(
                    Icons.close,
                    color: Color(0xffffd600),
                    size: screenHeight * 0.05,
                  ),
                  onPressed: () {
                    Navigator.of(context).pop();
                  },
                )),
            // search sections
            Positioned(
                top: screenHeight * 0.18,
                child: Container(
                    height: screenHeight * 0.73,
                    width: screenWidth,
                    child: Theme(
                      data: ThemeData(accentColor: Color(0x33ffffff)),
                      child: SingleChildScrollView(
                        child: Column(
                          children: [
                            FilterContent(
                              tags: [
                                "Giza",
                                "Luxor",
                                "Aswan",
                                "Cairo",
                                "Giza",
                                "Luxor",
                                "Deir al-Bahari",
                                "Valley of the Kings",
                                "Saqqara",
                                "Aswan",
                                "Tahrir Squar"
                              ],
                              title: "City",
                            ),
                            FilterContent(
                              tags: [
                                "2589",
                                "1550",
                                '1473',
                                "1336",
                                "1294",
                                '1147',
                                "2667",
                                "1264",
                                "332",
                                "1902"
                              ],
                              title: "Era",
                            ),
                            FilterContent(
                              tags: [
                                "MonumentalSite",
                                "Temple",
                                "Tomb",
                                "Museum"
                              ],
                              title: "Tags",
                            ),
                          ],
                        ),
                      ),
                    ))),
            // filter button
            Positioned(
                bottom: screenHeight * 0.05,
                width: screenWidth,
                child: Center(
                  child: InkWell(
                    child: ClipRRect(
                      borderRadius: BorderRadius.circular(screenHeight * 0.035),
                      child: Container(
                        width: screenWidth * 0.85,
                        height: screenHeight * 0.07,
                        color: Colors.black,
                        child: Center(
                          child: Text(
                            "Filter",
                            textAlign: TextAlign.center,
                            style: TextStyle(
                                color: Colors.white,
                                fontSize: screenHeight * 0.025,
                                fontWeight: FontWeight.bold),
                          ),
                        ),
                      ),
                    ),
                    onTap: () {
                      print("apply some search");
                    },
                  ),
                ))
          ],
        ),
      ),
    );
  }
}

class FilterContent extends StatelessWidget {
  double screenHeight, screenWidth;
  final String title;
  final List<String> tags;
  FilterContent({@required this.title, @required this.tags});
  @override
  Widget build(BuildContext context) {
    screenWidth = MediaQuery.of(context).size.width;
    screenHeight =
        MediaQuery.of(context).size.height - MediaQuery.of(context).padding.top;
    return Container(
      width: screenWidth * 0.9,
      child: Column(
        children: [
          Container(
              height: screenHeight * 0.07,
              width: screenWidth * 0.9,
              child: Align(
                  alignment: Alignment.centerLeft,
                  child: Text(
                    title,
                    style: TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize: screenHeight * 0.035),
                  ))),
          Container(
            width: screenWidth * 0.9,
            child: Wrap(
              alignment: WrapAlignment.start,
              children: [
                ...List.generate(
                    tags.length,
                    (index) => Container(
                          height: screenHeight * 0.05,
                          margin: EdgeInsets.only(
                              left: screenHeight * 0.02,
                              bottom: screenHeight * 0.01),
                          padding: EdgeInsets.only(
                              left: screenHeight * 0.02,
                              right: screenHeight * 0.02,
                              top: screenHeight * 0.01,
                              bottom: screenHeight * 0.01),
                          decoration: BoxDecoration(
                              borderRadius:
                                  BorderRadius.circular(screenHeight * 0.035),
                              color: Color(0xfffffd600)),
                          child: Text(
                            "#${tags[index]}",
                            textAlign: TextAlign.center,
                          ),
                        ))
              ],
            ),
          )
        ],
      ),
    );
  }
}

final Map tags = {
  "eras": [2589, 1550, 1473, 1336, 1294, 1147, 2667, 1264, 332, 1902],
  "governorates": ["Giza", "Luxor", "Aswan", "Cairo"],
  "locations": [
    "Giza",
    "Luxor",
    "Deir al-Bahari",
    "Valley of the Kings",
    "Saqqara",
    "Aswan",
    "Tahrir Squar"
  ]
};

/// types of filteration keyword
/// 1- governrate , location
/// 2- era
/// 3- sitesType
/// 4- kingName ????
///
///
/// the logic of filteration
/// for the above 4 options we have a
