package com.ak.parser;

import java.util.Arrays;

public class RemarkEnricher {

	public static void transforRemarks(ColumnRecord<String> record) {
		if (record.getValue().contains("ACH")) {
			// System.out.println("--- " + record.getValue() + " ---");
		}

		if (record.getValue().contains("-") && record.getValue().contains("/")
				&& record.getValue().indexOf("/") > record.getValue().indexOf("-")) {
			String[] arr = record.getValue().split("-");
			deligateToHandle(arr);
		} else if (record.getValue().contains("/")) {
			String[] arr = record.getValue().split("/");
			deligateToHandle(arr);
		} else if (record.getValue().contains("-")) {
			String[] arr = record.getValue().split("-");
			deligateToHandle(arr);
		} else if (record.getValue().contains(" ")) {
			String[] arr = record.getValue().split(" ");
			deligateToHandle(arr);
		} else {
			String[] arr = { record.getValue() };
			deligateToHandle(arr);
		}

	}

	public static void deligateToHandle(String[] arr) {
		if (arr[0].equalsIgnoreCase("NEFT")) {
			handleIncomingNEFT(arr);
		} else if (arr[0].equalsIgnoreCase("BIL")) {
			if (arr[1].equalsIgnoreCase("NEFT")) {
				handleOutgoingNEFT(arr);
			} else if (arr[1].equalsIgnoreCase("BPAY")) {
				handleBIL_BPAY(arr);
			} else if (arr[1].equalsIgnoreCase("ONL")) {
				handleBIL_ONL(arr);
			} else if (arr[1].startsWith("REV")) {
				// String[] arrUpdated = new String[2];
				// arrUpdated[0] = arr[0];
				arr[1] = arr[1].trim().substring(arr[1].trim().lastIndexOf(" "));
				handleBIL_REV(arr);
			}
		} else if (arr[0].equalsIgnoreCase("UPI")) {
			if (arr[1].startsWith("RVSL")) {
				String ref = arr[1].split("RVSL")[1];
				arr[1] = ref;
				handleUPI_RVSL(arr);
			} else {
				handleUPI(arr);
			}
		} else if (arr[0].equalsIgnoreCase("VPS")) {
			if (arr[1].startsWith("REF")) {
				handleVPS_REF(arr);
			} else if (arr[1].contains("RVSL")) {
				handleVPS_RVSL(arr);
			} else {
				handleVPS(arr);
			}
		} else if (arr[0].equalsIgnoreCase("MMT")) {
			if (arr[1].equalsIgnoreCase("IMPS")) {
				handleIMPS(arr);
			}
		} else if (arr[0].equalsIgnoreCase("IPS")) {
			handleIPS(arr);
		} else if (arr[0].equalsIgnoreCase("INF")) {
			handleINF(arr);
		} else if (arr[0].equalsIgnoreCase("EBA")) {
			handleEBA(arr);
		} else if (arr[0].equalsIgnoreCase("ACH")) {
			handleACH(arr);
		} else if (arr[0].contains("NACH")) {
			handleACH(arr);
		} else if (arr[0].contains("NFS")) {
			handleNFS(arr);
		} else if (arr[0].startsWith("LBPUN")) {
			handleLOAN(arr);
		} else if (arr[0].equalsIgnoreCase("ATD")) {
			handleATD(arr);
		} else if (arr[0].equalsIgnoreCase("VISA")) {
			handleVISA(arr);
		} else {
			System.err.println("Please Handle -> " + Arrays.toString(arr));
		}
	}

	public static void handleIncomingNEFT(String[] arr) {
		String mode = arr[0];
		String ref = arr[1];
		String payee = arr[2];
		// System.out.println(mode + " " + ref + " " + payee);
	}

	public static void handleOutgoingNEFT(String[] arr) {
		String mode = arr[1];
		String ref = arr[2];
		String remark = arr[3];
		String payer = arr[4];
		// System.out.println(mode + " " + ref + " " + remark + " " + payer);
	}

	public static void handleBIL_BPAY(String[] arr) {
		String mode = arr[1];
		String ref = arr[2];
		String payer = arr[3];
		// System.out.println(mode + " " + ref + " "+ payer);
	}

	public static void handleBIL_ONL(String[] arr) {
		String mode = arr[1];
		String ref = arr[2];
		String payer = arr[3];
		// System.out.println(mode + " " + ref + " "+ payer);
	}

	public static void handleBIL_REV(String[] arr) {
		String mode = arr[0] + "-" + "REV";
		String ref = arr[1];
		// System.out.println(mode + " " + ref);
	}

	public static void handleUPI(String[] arr) {
		String mode = arr[0];
		String ref = arr[1];
		String remarks = arr[2];
		String payer = arr[3];
		// System.out.println(mode + " " + ref + " "+ remarks+" "+payer);
	}

	public static void handleUPI_RVSL(String[] arr) {
		String mode = arr[0] + "-" + "RVSL";
		String ref = arr[1];
		String remarks = arr[2];
		String payer = arr[3];
		// System.out.println(mode + " " + ref + " "+ remarks+" "+payer);
	}

	public static void handleVPS(String[] arr) {
		// VPS/FEDERAL AUT/202010121249/028607118413/MEERUT
		String mode = arr[0];
		String payer = arr[1];
		String date = arr[2].substring(0, 8);
		String time = arr[2].substring(8, arr[2].length());
		String ref = arr[3];
		String location = "NA";
		if (arr.length == 5) {
			location = arr[4];
		}

		// System.out.println(mode + " " + payer + " " + date + " " + time + " " + ref +
		// " " + location);
	}

	public static void handleVPS_REF(String[] arr) {
		// VPS REF PUREFUELPOINT
		String mode = arr[0] + "-" + arr[1];
		String payer = "";
		for (int i = 2; i < arr.length; i++) {
			payer = payer.concat(" " + arr[i].trim());
		}
		// System.out.println(mode + " " + payer);
	}

	public static void handleVPS_RVSL(String[] arr) {
		// VPS/PAYMNT RVSL/Federal Aut/202101261750/102612119
		String mode = arr[0] + "-" + arr[1];
		String payer = arr[2];
		String date = arr[3].substring(0, 8);
		String time = arr[3].substring(8, arr[3].length());
		String ref = arr[4];
		// System.out.println(mode + " " + payer + " " + date + " " + time + " " + ref);
	}

	public static void handleIMPS(String[] arr) {
		// MMT/IMPS/032320151789/Avni Tikka/Ankit IDFC/IDFB00
		String mode = arr[1];
		String remark = arr[3];
		String payer = arr[4];
		String bank = arr[5];
		String ref = arr[2];
		// System.out.println(mode + " " + ref + " " + remark + " " + payer + " " +
		// bank);
	}

	public static void handleIPS(String[] arr) {
		// IPS/GANDHI STOR/202011011615/000000000219/MEERUT
		String mode = arr[0];
		String payer = arr[1];
		String date = arr[2].substring(0, 8);
		String time = arr[2].substring(8, arr[2].length());
		String ref = arr[3];
		String location = "NA";
		if (arr.length == 5) {
			location = arr[4];
		}

		// System.out.println(mode + " " + payer + " " + date + " " + time + " " + ref +
		// " " + location);
	}

	public static void handleINF(String[] arr) {
		// INF/INFT/000049904627/PPF/Self
		String mode = arr[0] + "-" + arr[1];
		String ref = arr[2];
		String remark = arr[3];
		String payer = "";
		if (arr.length == 5) {
			payer = arr[4];
		}
		// System.out.println(mode + " " + ref + " " + remark + " " + payer);
	}

	public static void handleEBA(String[] arr) {

		String mode = "";
		String date = "";
		String ref = "";

		if (arr[1].startsWith("BSE")) {
			// EBA/BSE N 202021209/20210128215902
			mode = arr[0] + "-" + arr[1].split(" ")[0];
			// date = arr[1].split(" ")[2];
			ref = arr[2];
		} else if (arr[1].equalsIgnoreCase("F&O")) {
			// EBA/F&O/11-Feb-2021/20210211224242
			mode = arr[0] + "-" + arr[1];
			date = arr[2];
			ref = arr[3];
		} else if (arr[1].startsWith("PUR")) {
			// EBA/PUR-IIFL FINANCE LIM/20210324173007
			mode = arr[0] + "-" + "BOND" + "-" + arr[1].split("-")[1];
			// date = arr[1].split("-")[1];
			ref = arr[2];
		} else {

		}
		// System.out.println(mode + " " + date + " " + ref);
	}

	public static void handleACH(String[] arr) {
		if (arr[0].startsWith("ACH")) {
			// ACH/INDIAN CLEARING CORP/P5165266X010752412
			String mode = arr[0];
			String ref = arr[2];
			String remark = arr[1];
			// System.out.println(mode + " " + remark + " " + ref);
		} else {
			// NACH_AD_RTN_CHRG~IPRULI~06APR20~adfe883~20897529GS
			if (arr[0].startsWith("NACH_AD_RTN_CHRG")) {
				String mode = "ACH RETURN";
				String ref = arr[0].split("~")[4];
				String remark = arr[0].split("~")[1];
				String date = arr[0].split("~")[2];
				// System.out.println(mode + " " + remark + " " + date+" "+ref);
			}
		}

	}

	public static void handleNFS(String[] arr) {
		// NFS/N4798300/CASH WDL/11-08-20
		String mode = "ATM";
		String date = arr[3];
		String remarks = arr[2];
		String ref = arr[1];
		if (remarks.contains("RVSL")) {
			mode = mode + "-" + "RVSL";
		}
		// System.out.println(mode + " " + ref + " " + remarks + " " + date);
	}

	public static void handleLOAN(String[] arr) {
		// LBPUNXX01090 APR20 Ankit Kuma
		String mode = "LOAN";
		String date = arr[1];
		String ref = arr[0];

		// System.out.println(mode + " " + date + " " + ref);
	}

	private static void handleATD(String[] arr) {
		// ATD/Auto Debit CC0xx2414
		String mode = "ATD";
		String remark = "Auto Debit";
		String ref = arr[1].split(" ")[2];

		System.out.println(mode + " " + remark + " " + ref);
	}

	private static void handleVISA(String[] arr) {
		// VISA REF KRISHNA FILLING
		String mode = arr[0];
		String payer = "";

		for (int i = 2; i < arr.length; i++) {
			payer = payer + " " + arr[i];
		}

		System.out.println(mode + " " + payer);
	}

	
}
