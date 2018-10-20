package org.alvin.cishan.sys.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.alibaba.fastjson.util.IOUtils.close;

public class CMDUtils {

	public static int execute(String cmd, StringBuffer resultMessage, StringBuffer errorMessage) throws IOException, InterruptedException {
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().contains("windows")) {
			return executWindowsCmd(cmd, resultMessage, errorMessage);
		} else {
			return executLinuxCmd(cmd, resultMessage, errorMessage);
		}
	}

	private static int executLinuxCmd(String cmd, StringBuffer resultMessage, StringBuffer errorMessage) throws IOException, InterruptedException {
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec(new String[]{"sh", "-c", cmd});
		return run(resultMessage, errorMessage, proc);
	}

	private static int executWindowsCmd(String cmd, StringBuffer resultMessage, StringBuffer errorMessage) throws IOException, InterruptedException {
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec(new String[]{"cmd", "/c", cmd});
		return run(resultMessage, errorMessage, proc);
	}

	private static int run(StringBuffer resultMessage, StringBuffer errorMessage, Process proc) throws IOException, InterruptedException {
		int exitVal;
		try {
			InputStream stdin = proc.getInputStream();
			InputStreamReader isr = new InputStreamReader(stdin);
			BufferedReader br = new BufferedReader(isr);
			BufferedReader error = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			String line;
			if (resultMessage != null) {
				while ((line = br.readLine()) != null) {
					resultMessage.append(line).append("\n");
				}
			}
			if (errorMessage != null) {
				while ((line = error.readLine()) != null) {
					errorMessage.append(line).append("\n");
				}
			}
			exitVal = proc.waitFor();
		} finally {
			close(proc.getOutputStream());
			close(proc.getInputStream());
			close(proc.getErrorStream());
			proc.destroy();
		}
		return exitVal;
	}

}
