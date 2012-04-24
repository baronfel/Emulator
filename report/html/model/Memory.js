var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":55,"id":2279,"methods":[{"el":25,"sc":2,"sl":18},{"el":35,"sc":2,"sl":27},{"el":44,"sc":2,"sl":37},{"el":49,"sc":2,"sl":46},{"el":54,"sc":2,"sl":51}],"name":"Memory","sl":13}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_2":{"methods":[{"sl":18},{"sl":37},{"sl":46},{"sl":51}],"name":"EncodeTest","pass":true,"statements":[{"sl":20},{"sl":22},{"sl":23},{"sl":39},{"sl":40},{"sl":42},{"sl":48},{"sl":53}]},"test_4":{"methods":[{"sl":18},{"sl":37},{"sl":46},{"sl":51}],"name":"DecodeTest","pass":true,"statements":[{"sl":20},{"sl":22},{"sl":23},{"sl":39},{"sl":40},{"sl":42},{"sl":48},{"sl":53}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [2, 4], [], [2, 4], [], [2, 4], [2, 4], [], [], [], [], [], [], [], [], [], [], [], [], [], [2, 4], [], [2, 4], [2, 4], [], [2, 4], [], [], [], [2, 4], [], [2, 4], [], [], [2, 4], [], [2, 4], [], []]
