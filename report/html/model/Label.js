var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":28,"id":2302,"methods":[{"el":15,"sc":2,"sl":13},{"el":19,"sc":2,"sl":16},{"el":23,"sc":2,"sl":21},{"el":27,"sc":2,"sl":25}],"name":"Label","sl":9}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_18":{"methods":[{"sl":21},{"sl":25}],"name":"LabelTest","pass":true,"statements":[{"sl":22},{"sl":26}]},"test_2":{"methods":[{"sl":16},{"sl":21},{"sl":25}],"name":"EncodeTest","pass":true,"statements":[{"sl":17},{"sl":18},{"sl":22},{"sl":26}]},"test_4":{"methods":[{"sl":16},{"sl":21},{"sl":25}],"name":"DecodeTest","pass":true,"statements":[{"sl":17},{"sl":18},{"sl":22},{"sl":26}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [2, 4], [2, 4], [2, 4], [], [], [18, 2, 4], [18, 2, 4], [], [], [18, 2, 4], [18, 2, 4], [], []]
