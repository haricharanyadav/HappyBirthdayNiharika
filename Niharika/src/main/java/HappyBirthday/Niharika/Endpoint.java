package HappyBirthday.Niharika;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@RestController
public class Endpoint {

	@Autowired
	private MyResourceHttpRequestHandler handler;

	@Component
	final static class MyResourceHttpRequestHandler extends ResourceHttpRequestHandler {

		private final static String ATTR_FILE = MyResourceHttpRequestHandler.class.getName() + ".file";

		@Override
		protected Resource getResource(HttpServletRequest request) throws IOException {

			final File file = (File) request.getAttribute(ATTR_FILE);
			return new FileSystemResource(file);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String welcome() {
		StringBuilder contentBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/main/java/index.html"));
			String str;
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		} catch (IOException e) {
		}
		String content = contentBuilder.toString();
		return content;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/index.js")
	public String js() {
		StringBuilder contentBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/main/java/index.js"));
			String str;
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		} catch (IOException e) {
		}
		String content = contentBuilder.toString();
		return content;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/index.css")
	public String css() {
		StringBuilder contentBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader("src/main/java/index.css"));
			String str;
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		} catch (IOException e) {
		}
		String content = contentBuilder.toString();
		return content;
	}

	private ResourceLoader resourceLoader = new DefaultResourceLoader();

	@ResponseBody
	@RequestMapping(value = "/image/{image}", produces = "image/bmp")
	public Resource texture(@PathVariable("image") String image) {
		return resourceLoader.getResource(image);
	}

}
