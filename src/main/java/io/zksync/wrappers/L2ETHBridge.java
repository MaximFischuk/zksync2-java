package io.zksync.wrappers;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class L2ETHBridge extends Contract {
    public static final String BINARY = "0x0000014d04000041000000000141016f0000014e0400004100000000001403760000014f010000410000000000210376000000000130004c000000090000613d0530000a0000034f053000d20000034f00080000000000020000008001000039000000400200003900000000001203760000000001000357000000000110004c000000230000c13d000100000002001d0000014f01000041000300000001001d00000000020103750000008001000039000200000001001d000400000002001d0530019b0000034f00000003010000290000000001010375000000000110004c000000250000c13d000000800100003900000000020000190000000403000029053005210000034f0000000407000029000000450000013d0000000001000019000005320001037200000004070000290000001f0170018f0000014e0200004100000000020203750000000503700270000000000430004c0000000004000019000000350000613d000000050540021000000000065200190000000006060377000000800550003900000000006503760000000104400039000000000534004b0000002d0000413d000000000310004c000000450000613d000000200300008a000000000337016f000000000232001900000003011002100000008003300039000000000403037500000000041401cf000000000414022f00000000020203770000010001100089000000000212022f00000000011201cf000000000141019f000000000013037600000150010000410000001f0270008c00000000020000190000000002012019000000000317016f000000000430004c0000000001008019000001500330009c000000000102c019000000000110004c000000520000c13d0000000001000019000005320001037200000002010000290000000002010375000001510120009c000000580000413d000000000100001900000532000103720000000201000039000400000001001d000300000002001d053005160000034f0000015202000041000000000121016f0000000302000029000000000121019f0000000402000029053005140000034f0000015301000041000001540200004100000000001203760000800a0100003900000155020000410000000000120376000080020100003900000156020000410000000003000356000000080330008a00000020033000c9053004dc0000034f00000157030000410000015804000041000000200210011a0000000005020031000000000245016f00000000002303760000004003500270000000000343016f00000159040000410000000000340376000000ff03000039000000200110011a000000010131025f000000010110008c000000800000c13d0000000001020378000000000110004c000000820000c13d00000000010000190000053200010372000400000003001d000000010100002900000000040103750000015a0100004100000000001403760000015b010000410000000002000350000000000112016f00000004024000390000000003000355000000000012037600000060013002100000015c02000041000000000121016f0000015802000041000300000004001d000000000224016f000000000112019f0000015d02000041000000000221019f0000800a010000390000000003000356000000060330008a00000020033000c900cd04a40000034f0000000306000029000000200210011a0000000403000029000000010332025f000000200110011a00000000010100310000015802000041000000000421016f000001570500004100000000004503760000004001100270000000000221016f00000159010000410000000000210376000000000130004c000000d10000613d00000000010600190530019b0000034f00000159010000410000000001010375000000010200008a0000015003000041000000000221004b00000000020000190000000002032019000000000131016f000000000431013f000001500110009c00000000010000190000000001034019000001500340009c000000000102c019000000000110004c000000bf0000c13d000000000100001900000532000103720000014f010000410000000001010375000000000110004c000000c80000c13d0000000101000029000000000101037500000000020000190000000003000019053005210000034f0000002001000039000000000010037600000000000103760000015e0100004100000531000103700000015701000041000000000001037600000159010000410000000000010376053001b10000034f000a0000000000020000008006000039000000400500003900000000006503760000014f010000410000000001010375000000040110008c000001950000413d0000014e01000041000000000101037500000000010103770000015f02000041000000000121016f000001600210009c000000f70000c13d0000000001000357000000000110004c000001090000c13d0000014f010000410000000001010375000000040110008a000000010200008a0000015003000041000000000221004b00000000020000190000000002032019000000000131016f000000000431013f000001500110009c00000000010000190000000001034019000001500340009c000000000102c019000000000110004c000001230000c13d00000000010000190000053200010372000001610210009c0000010b0000c13d0000000001000357000000000110004c0000012a0000c13d0000014f010000410000000001010375000100000005001d053001dd0000034f053001f50000034f0000000102000029000000000202037500000000001203760000015801000041000000000112016f0000016702000041000000000121019f000005310001037000000000010000190000053200010372000001620210009c0000012c0000c13d0000000001000357000000000110004c000001480000c13d0000014f010000410000000001010375000000040110008a000000010200008a0000015003000041000000000221004b00000000020000190000000002032019000000000131016f000000000431013f000001500110009c00000000010000190000000001034019000001500340009c000000000102c019000000000110004c000001620000c13d000000000100001900000532000103720000000001000019000100000006001d053005160000034f000000010200002900000000001203760000016801000041000005310001037000000000010000190000053200010372000001630210009c0000014a0000c13d0000000001000357000000000110004c0000016f0000c13d0000014f010000410000000002010375000500000000001d000600000000001d000700000000001d000800000000001d000900000000001d000a00000000001d00000000010003560000000a0110008a00000020011000c9000100000005001d0530022f0000034f0000000a01000029000000090200002900000008030000290000000704000029053002e50000034f000000010100002900000000010103750000015802000041000000000121016f000005310001037000000000010000190000053200010372000001640210009c000001710000c13d0000000001000357000000000110004c000001820000c13d0000014f010000410000000002010375000200000000001d000300000000001d000400000000001d0000000001000356000000040110008a00000020011000c9000100000005001d0530028f0000034f000000040100002900000003020000290000000203000029053003810000034f000000010100002900000000010103750000015802000041000000000121016f00000531000103700000000201000039000100000005001d053005160000034f0000015b02000041000000000121016f0000000102000029000000000202037500000000001203760000015801000041000000000112016f0000016702000041000000000121019f000005310001037000000000010000190000053200010372000001650210009c000001840000c13d0000000001000357000000000110004c000001970000c13d0000014f010000410000000001010375000100000005001d053001dd0000034f0000000101000029000000000101037500000000000103760000015802000041000000000121016f0000016702000041000000000121019f000005310001037000000000010000190000053200010372000001660110009c000001950000c13d0000000001000357000000000110004c000001990000c13d0000014f010000410000000001010375000100000005001d053001dd0000034f0000000101000029000000000101037500000000000103760000015802000041000000000121016f0000016702000041000000000121019f00000531000103700000000001000019000005320001037200000000010000190000053200010372000000000100001900000532000103720000001f02200039000000200300008a000000000232016f0000000001120019000000000221004b00000000020000190000000102004039000001580310009c000001aa0000213d000000010220018f000000000220004c000001aa0000c13d00000040020000390000000000120376000000000001036f000001690100004100000000001003760000004101000039000000040200003900000000001203760000015d0100004100000532000103720000015701000041000000000201037500000040010000390000000001010375000001590300004100000000040303750000001f0340018f0000000505400270000000000650004c0000000006000019000001c40000613d000000050760021000000000087100190000000007720019000000000707037800000000007803760000000106600039000000000756004b000001bc0000413d000000000530004c000001d40000613d000000200500008a000000000454016f000000000242001900000000044100190000000303300210000000000504037500000000053501cf000000000535022f00000000020203780000010003300089000000000232022f00000000023201cf000000000252019f00000000002403760000015802000041000000000121016f0000015902000041000000000202037500000040022002100000016a03000041000000000232016f000000000112019f0000053200010372000000040110008a00000150020000410000001f0310008c00000000030000190000000003022019000000000121016f000000000410004c0000000002008019000001500110009c00000000010300190000000001026019000000000110004c000001f10000613d0000014e01000041000000000101037500000004011000390000000001010377000001510210009c000001f30000813d000000000001036f000000000100001900000532000103720000000001000019000005320001037200020000000000020000015b02000041000000000121016f000000000010037600000001010000390000002002000039000000000012037600008010010000390000015e020000410000000003000356000000020330008a00000020033000c9053004dc0000034f000000ff02000039000000200310011a000000010323025f000000200110011a00000000020100310000015801000041000000000112016f000000010330008c0000020f0000c13d0000000001010378053005160000034f0000000200000005000000000001036f00000040032002700000016b040000410000015802000041000000000223016f0000001f0530018f0000000506200270000000000760004c00000000070000190000021f0000613d00000005087002100000000009810019000000000909037800000000009803760000000107700039000000000867004b000002180000413d000000000650004c0000022d0000613d000000000343016f0000000304500210000000000503037500000000054501cf000000000545022f000000000131001900000000010103780000010004400089000000000141022f00000000014101cf000000000151019f000000000013037600000040012002100000053200010372000000040320008a00000150040000410000009f0530008c00000000050000190000000005042019000000000343016f000000000630004c0000000004008019000001500330009c00000000030500190000000003046019000000000330004c0000027f0000613d0000014e03000041000000000303037500000004033000390000000003030377000001510430009c000002810000813d000000200410011a000000000403001f0000014e03000041000000000303037500000024033000390000000003030377000001510430009c000002830000813d0000002004100039000000200440011a000000000403001f0000014e03000041000000000303037500000044033000390000000003030377000001510430009c000002850000813d00000060041000390000004005100039000000200550011a000000000503001f0000014e03000041000000000503037500000064055000390000000005050377000000200440011a000000000405001f0000000004030375000000840340003900000000030303770000016c0530009c000002870000813d00000023053000390000015006000041000000000725004b00000000070000190000000007064019000000000862016f000000000565016f000000000985004b000000000600a019000000000585013f000001500550009c00000000050700190000000005066019000000000550004c000002890000613d0000000004340019000000040440003900000000040403770000016c0540009c0000028b0000813d00000024033000390000000005430019000000000225004b0000028d0000213d000000200210011a000500000204001f000000200210011a000400000203001f000000000001036f0000000001000019000005320001037200000000010000190000053200010372000000000100001900000532000103720000000001000019000005320001037200000000010000190000053200010372000000000100001900000532000103720000000001000019000005320001037200000000010000190000053200010372000000040220008a00000150030000410000005f0420008c00000000040000190000000004032019000000000232016f000000000520004c0000000003008019000001500220009c00000000020400190000000002036019000000000220004c000002b30000613d0000014e02000041000000000202037500000004022000390000000002020377000001510320009c000002b50000813d000000200310011a000000000302001f0000014e02000041000000000202037500000024022000390000000002020377000001510320009c000002b70000813d000000200310011a000100000302001f0000014e02000041000000000202037500000044022000390000000002020377000000200310011a000200000302001f000000000001036f0000000001000019000005320001037200000000010000190000053200010372000000000100001900000532000103720000015701000041000000000201037500000040010000390000000001010375000001590300004100000000040303750000001f0340018f0000000505400270000000000650004c0000000006000019000002cc0000613d000000050760021000000000087100190000000007720019000000000707037800000000007803760000000106600039000000000756004b000002c40000413d000000000530004c000002dc0000613d000000200500008a000000000454016f000000000242001900000000044100190000000303300210000000000504037500000000053501cf000000000535022f00000000020203780000010003300089000000000232022f00000000023201cf000000000252019f00000000002403760000015802000041000000000121016f0000015902000041000000000202037500000040022002100000016a03000041000000000232016f000000000112019f0000053200010372000a000000000002000600000004001d000500000003001d000400000002001d000300000001001d0000000201000039053005160000034f0000015b02000041000000000121016f0000000002000351000000000112004b0000036f0000c13d0000015b010000410000000502000029000000000112016f000000000110004c000003710000c13d0000015301000041000001540200004100000000001203760000800a01000039000001550200004100000000001203760000800201000039000001560200004100000000030003560000000a0330008a00000020033000c9053004dc0000034f000000200210011a000000000302003100000157040000410000015805000041000000000253016f00000000002403760000004003300270000000000353016f00000159040000410000000000340376000000ff03000039000000200110011a000500000003001d000000010131025f000000010110008c0000036d0000c13d0000000001020378000000000110004c0000036d0000613d0000004001000039000200000001001d00000000040103750000016d01000041000000000014037600000024014000390000000002000355000000060300002900000000003103760000015b010000410000000403000029000000000313016f0000000401400039000100000003001d000000000031037600000060012002100000015c02000041000000000121016f0000015802000041000400000004001d000000000224016f000000000112019f0000016e02000041000000000221019f0000800a010000390000000003000356000000080330008a00000020033000c9037c04a40000034f000000200210011a0000000503000029000000010232025f000000200110011a00000000010100310000015803000041000000000431016f000001570500004100000000004503760000004001100270000000000131016f00000159030000410000000000130376000000000220004c000003800000613d0000001f011000390000016f02000041000000000221016f00000004010000290000000001120019000000000221004b00000000020000190000000102004039000001580310009c000003730000213d000000010220018f000000000220004c000003730000c13d0000000202000029000000000012037600000159020000410000000002020375000000010300008a0000015004000041000000000332004b00000000030000190000000003042019000000000242016f000000000542013f000001500220009c00000000020000190000000002044019000001500450009c000000000203c019000000000220004c0000037a0000613d000000060300002900000000003103760000017001000041000001710200004100000000001203600000015b010000410000000302000029000000000112016f0000000102000029000000000021035f000000000030035f0000000a00000005000000000001036f000000000100001900000532000103720000000001000019000005320001037200000000010000190000053200010372000001690100004100000000001003760000004101000039000000040200003900000000001203760000015d010000410000053200010372000000000100001900000532000103720000015701000041000000000001037600000159010000410000000000010376053002b90000034f000a000000000002000600000003001d000400000001001d0000015b01000041000000000112016f000000000110004c000004130000c13d0000015301000041000001540200004100000000001203760000800a01000039000001550200004100000000001203760000800201000039000001560200004100000000030003560000000a0330008a00000020033000c9053004dc0000034f000000200210011a000000000302003100000157040000410000015805000041000000000253016f00000000002403760000004003300270000000000353016f00000159040000410000000000340376000000ff03000039000000200110011a000300000003001d000000010131025f000000010110008c000004110000c13d0000000001020378000000000110004c000004110000613d0000004001000039000500000001001d00000000040103750000017201000041000000000014037600000024014000390000000002000355000000060300002900000000003103760000015b010000410000000003000351000100000003001d000000000113016f0000000403400039000000000013037600000060012002100000015c02000041000000000121016f0000015802000041000200000004001d000000000224016f000000000112019f0000016e02000041000000000221019f0000800a010000390000000003000356000000080330008a00000020033000c9042504a40000034f000000200210011a0000000303000029000000010232025f000000200110011a00000000010100310000015803000041000000000431016f000001570500004100000000004503760000004001100270000000000131016f00000159030000410000000000130376000000000220004c0000000407000029000004290000613d0000001f011000390000016f02000041000000000221016f00000002010000290000000001120019000000000221004b00000000020000190000000102004039000001580310009c0000000606000029000004150000213d000000010220018f000000000220004c000004150000c13d00000005020000290000000000120376000001590200004100000000020203750000015003000041000000000420004c00000000040000190000000004034019000000000232016f000000000520004c000000000300a019000001500220009c00000000020400190000000002036019000000000220004c0000041c0000c13d0000002002100039000001730300004100000000003203760000006002700210000000240310003900000000002303760000003802100039000000000062037600000038020000390000000000210376000001740210009c0000041e0000813d0000006002100039000000050300002900000000002303760530042a0000034f00000005010000290000000001010375000000060300002900000000003103760000017501000041000001710200004100000000001203600000015b010000410000000402000029000000000112016f0000000102000029000000000012035f000000000030035f0000000a00000005000000000001036f0000000001000019000005320001037200000000010000190000053200010372000001690100004100000000001003760000004101000039000000040200003900000000001203760000015d01000041000005320001037200000000010000190000053200010372000001690100004100000000001003760000004101000039000000040200003900000000001203760000015d0100004100000532000103720000015701000041000000000001037600000159010000410000000000010376053002b90000034f00040000000000020000004002000039000100000002001d0000000007020375000001760200004100000000002703760000000402700039000000200300003900000000003203760000000002010375000000240370003900000000002303760000004403700039000000000420004c0000000004000019000004450000613d00000000054300190000002004400039000000000614001900000000060603750000000000650376000000000524004b0000043a0000413d000000000124004b000004450000a13d00000000012300190000000000010376000000000100035500000020011002100000017703000041000000000131016f0000001f022000390000017803000041000000000232016f00000044022000390000017903000041000000000232016f000000000121019f0000015802000041000200000007001d000000000227016f0000004001100210000000000221019f00008008010000390000000003000356000000040330008a00000020033000c9049f04a40000034f000000ff02000039000000200310011a000000010223025f000000200110011a00000000010100310000015804000041000000000341016f0000004001100270000000000141016f00000000040000190000000207000029000000050540021000000000065700190000000005530019000000000505037800000000005603760000000105400039000000000445004b00000000040000190000000104004039000000010440018f000000000440004c0000000004050019000004650000c13d0000015704000041000000000034037600000159030000410000000000130376000000000220004c000004a30000613d0000001f011000390000016f02000041000000000221016f0000000001720019000000000221004b00000000020000190000000102004039000001580310009c000004960000213d000000010220018f000000000220004c000004960000c13d000000010200002900000000001203760000015901000041000000000101037500000150020000410000001f0310008c00000000030000190000000003022019000000000121016f000000000410004c0000000002008019000001500110009c00000000010300190000000001026019000000000110004c0000049d0000613d0000000400000005000000000001036f000001690100004100000000001003760000004101000039000000040200003900000000001203760000015d010000410000053200010372000000000100001900000532000103720000015701000041000000000001037600000159010000410000000000010376053002b90000034f0002000000000002000200000003001d0000002003300039000100000003001d000004bf002103630000000205000029000000200250011a000000000201001f0000000106000029000000202160011a000000000126004900000003022002100000010003200089000000200410011a000000010334025f000000200110011a000000000101003100000000042101cf000000000343019f000000000220004c000000000103c0190000015002000041000000200360011a00000000032101a500000000010500190000000200000005000000000001036f0000000103000029000000020500002900000000020000190000000102004039000000010220018f000000000220004c000004db0000c13d000000200250011a000000000201001f0000000006030019000000202160011a000000000126004900000003022002100000010003200089000000200410011a000000010334025f000000200110011a000000000101003100000000042101cf000000000343019f000000000220004c000000000103c0190000017a02000041000000200360011a000000000321017500000000010500190000000200000005000000000001036f00000000000103710002000000000002000200000003001d0000002003300039000100000003001d000004f7002103650000000205000029000000200250011a000000000201001f0000000106000029000000202160011a000000000126004900000003022002100000010003200089000000200410011a000000010334025f000000200110011a000000000101003100000000042101cf000000000343019f000000000220004c000000000103c0190000015002000041000000200360011a00000000032101a500000000010500190000000200000005000000000001036f0000000103000029000000020500002900000000020000190000000102004039000000010220018f000000000220004c000005130000c13d000000200250011a000000000201001f0000000006030019000000202160011a000000000126004900000003022002100000010003200089000000200410011a000000010334025f000000200110011a000000000101003100000000042101cf000000000343019f000000000220004c000000000103c0190000017a02000041000000200360011a000000000321017500000000010500190000000200000005000000000001036f0000000000010371000000000012035b000000000001036f0000000001010359000000000001036f000000000401037500000000043401cf000000000434022f0000010003300089000000000232022f00000000023201cf000000000242019f0000000000210376000000000001036f0000000504300270000000000540004c000005290000613d00000000002103760000002001100039000000010440008a000000000540004c000005240000c13d0000001f0330018f000000000430004c0000052f0000613d0000000303300210053005180000034f000000000001036f000000000001036f000005300000037400000531000103700000053200010372000000000000e0010000000000000000000000000000000000000000000000000000000000ffffff0000000000000000000000000000000000000000000000000000000000ffffe00000000000000000000000000000000000000000000000000000000000ffffc080000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000ffffffffffffffffffffffff0000000000000000000000000000000000000000b51c4f962e15e63fb3aaa3805ce73bd64f18d4b3b30130e6b1863a0452f875a40000000000000000000000000000000000000000000000000000000000fff8000000000000000000000000000000000000000000000000000000000000fff8040000000000000000000000000000000000000000000000240000000000fff8000000000000000000000000000000000000000000000000000000000000ffffa0000000000000000000000000000000000000000000000000ffffffffffffffff0000000000000000000000000000000000000000000000000000000000ffff8090b4b79c00000000000000000000000000000000000000000000000000000000000000000000000000000000ffffffffffffffffffffffffffffffffffffffff00000000000000000000000000000000ffffffff00000000000000000000000000000000000000000000000000000000000000000000002400000000000000000000000000000000000000000000000000000000000000400000000000000000ffffffff0000000000000000000000000000000000000000000000000000000018160ddd0000000000000000000000000000000000000000000000000000000070a0823100000000000000000000000000000000000000000000000000000000969b53da00000000000000000000000000000000000000000000000000000000cfe7af7c00000000000000000000000000000000000000000000000000000000d9caed1200000000000000000000000000000000000000000000000000000000f54266a200000000000000000000000000000000000000000000000000000000f5f1516800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000000000000000000002000000000000000804e487b710000000000000000000000000000000000000000000000000000000000000000000000000000000000000000ffffffffffffffff0000000000000000000000000000000000000000000000000000000000000000ffffffffffffffe000000000000000000000000000000000000000000000000100000000000000008c2a993e000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000440000000000000000000000000000000000000000000000000000000000000001ffffffffffffffe0b84fba9af218da60d299dc177abd5805e7ac541d2673cbee7808c10017874f63000000000000000000000000000000000000000000000000000000200000000474f4f54700000000000000000000000000000000000000000000000000000000882f6b9600000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000ffffffffffffffa03115d1449a7b732c986cba18244e897a450f61e1bb8d589cd2e69e6c8924f9f762f84b2400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000ffffffff0000000000000000000000000000000000000000000000000000000000000000ffffffe000000000000000000000000000000000000000000000000000000000ffffffe47fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_FINALIZEDEPOSIT = "finalizeDeposit";

    public static final String FUNC_L1BRIDGE = "l1Bridge";

    public static final String FUNC_L1TOKENADDRESS = "l1TokenAddress";

    public static final String FUNC_L2TOKENADDRESS = "l2TokenAddress";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final Event FINALIZEDEPOSIT_EVENT = new Event("FinalizeDeposit", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event WITHDRAW_EVENT = new Event("Withdraw", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
    }

    @Deprecated
    protected L2ETHBridge(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected L2ETHBridge(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected L2ETHBridge(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected L2ETHBridge(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<FinalizeDepositEventResponse> getFinalizeDepositEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(FINALIZEDEPOSIT_EVENT, transactionReceipt);
        ArrayList<FinalizeDepositEventResponse> responses = new ArrayList<FinalizeDepositEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FinalizeDepositEventResponse typedResponse = new FinalizeDepositEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.l1Sender = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.l2Receiver = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.l2Token = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<FinalizeDepositEventResponse> finalizeDepositEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, FinalizeDepositEventResponse>() {
            @Override
            public FinalizeDepositEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(FINALIZEDEPOSIT_EVENT, log);
                FinalizeDepositEventResponse typedResponse = new FinalizeDepositEventResponse();
                typedResponse.log = log;
                typedResponse.l1Sender = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.l2Receiver = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.l2Token = (String) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<FinalizeDepositEventResponse> finalizeDepositEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FINALIZEDEPOSIT_EVENT));
        return finalizeDepositEventFlowable(filter);
    }

    public List<WithdrawEventResponse> getWithdrawEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(WITHDRAW_EVENT, transactionReceipt);
        ArrayList<WithdrawEventResponse> responses = new ArrayList<WithdrawEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            WithdrawEventResponse typedResponse = new WithdrawEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.l2Sender = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.l1Receiver = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.l2Token = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<WithdrawEventResponse> withdrawEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, WithdrawEventResponse>() {
            @Override
            public WithdrawEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(WITHDRAW_EVENT, log);
                WithdrawEventResponse typedResponse = new WithdrawEventResponse();
                typedResponse.log = log;
                typedResponse.l2Sender = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.l1Receiver = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.l2Token = (String) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<WithdrawEventResponse> withdrawEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WITHDRAW_EVENT));
        return withdrawEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> finalizeDeposit(String _l1Sender, String _l2Receiver, String _l1Token, BigInteger _amount, byte[] param4) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_FINALIZEDEPOSIT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_l1Sender), 
                new org.web3j.abi.datatypes.Address(_l2Receiver), 
                new org.web3j.abi.datatypes.Address(_l1Token), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount), 
                new org.web3j.abi.datatypes.DynamicBytes(param4)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> l1Bridge() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_L1BRIDGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> l1TokenAddress(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_L1TOKENADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> l2TokenAddress(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_L2TOKENADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw(String _l1Receiver, String _l2Token, BigInteger _amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_l1Receiver), 
                new org.web3j.abi.datatypes.Address(_l2Token), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static L2ETHBridge load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new L2ETHBridge(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static L2ETHBridge load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new L2ETHBridge(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static L2ETHBridge load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new L2ETHBridge(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static L2ETHBridge load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new L2ETHBridge(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<L2ETHBridge> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _l1Bridge) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_l1Bridge)));
        return deployRemoteCall(L2ETHBridge.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<L2ETHBridge> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _l1Bridge) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_l1Bridge)));
        return deployRemoteCall(L2ETHBridge.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<L2ETHBridge> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _l1Bridge) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_l1Bridge)));
        return deployRemoteCall(L2ETHBridge.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<L2ETHBridge> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _l1Bridge) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_l1Bridge)));
        return deployRemoteCall(L2ETHBridge.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class FinalizeDepositEventResponse extends BaseEventResponse {
        public String l1Sender;

        public String l2Receiver;

        public String l2Token;

        public BigInteger amount;
    }

    public static class WithdrawEventResponse extends BaseEventResponse {
        public String l2Sender;

        public String l1Receiver;

        public String l2Token;

        public BigInteger amount;
    }
}