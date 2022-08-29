package id.texo.fingerserver.utility

class FileUtils {

	public static File createDirs(String path) {
		File f = new File(path);
		if (!f.exists()) {
			return f.mkdirs() ? f : null;
		}
		return f;
	}

	public static File export(String base64data, String filepath) {
		FileOutputStream fos = null;
		try {
			byte[] data = Base64.getDecoder().decode(base64data);
			File file = new File(filepath);
			fos = new FileOutputStream(file);
			fos.write(data);
			return file;
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
		return null;
	}

	public static boolean delete(String path, boolean deleteContent) {
		File f1 = new File(path);
		if (!f1.exists()) {
			return true;
		}
		if (deleteContent) {
			boolean deletedAll = true;
			File[] files = listFiles(path, true);
			sort(files, true); // Sort descending before deletion
			for (File f2 : files) {
				deletedAll = deletedAll && f2.delete();
			}
			return deletedAll;
		}
		return f1.delete();
	}

	public static boolean delete(String path) {
		return delete(path, false);
	}

	public static File[] listFiles(String path, boolean includeDir) {
		File f1 = new File(path);
		if (!f1.isDirectory()) {
			return new File[f1];
		}
		List<File> list = new ArrayList<>();
		Queue<File> queue = new LinkedList<>();
		queue.add(f1);
		while (!queue.isEmpty()) {
			File f2 = queue.poll();
			if (f2.isDirectory()) {
				File[] files = f2.listFiles();
				queue.addAll(Arrays.asList(files));
				if (includeDir) {
					list.add(f2);
				}
			} else {
				list.add(f2);
			}
		}
		return list.toArray(new File[list.size()]);
	}

	public static File[] listFiles(String path) {
		return listFiles(path, false);
	}

	public static String toBase64(File file) throws IOException {
		FileInputStream fileInputStreamReader = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		fileInputStreamReader.read(bytes);
		return Base64.getEncoder().encodeToString(bytes);
	}

}