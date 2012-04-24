var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":102,"id":453,"methods":[{"el":29,"sc":2,"sl":22},{"el":39,"sc":2,"sl":36},{"el":52,"sc":2,"sl":48},{"el":66,"sc":2,"sl":61},{"el":75,"sc":2,"sl":73},{"el":88,"sc":2,"sl":83},{"el":101,"sc":2,"sl":96}],"name":"Serializer","sl":20}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_12":{"methods":[{"sl":22},{"sl":73}],"name":"SerializedPopulatedConfigEqualsPopulatedConfigString","pass":true,"statements":[{"sl":24},{"sl":27},{"sl":28},{"sl":74}]},"test_16":{"methods":[{"sl":22},{"sl":73}],"name":"SerializedDefaultConfigEqualsDefaultConfigObject","pass":false,"statements":[{"sl":24},{"sl":27},{"sl":28},{"sl":74}]},"test_19":{"methods":[{"sl":22},{"sl":36}],"name":"DefaultConfigSerializesCorrectly","pass":false,"statements":[{"sl":24},{"sl":27},{"sl":28},{"sl":38}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [19, 12, 16], [], [19, 12, 16], [], [], [19, 12, 16], [19, 12, 16], [], [], [], [], [], [], [], [19], [], [19], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [12, 16], [12, 16], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], []]
