package operators;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import configs.Config;

public enum Database {
	INSTANCE;
	private JdbcTemplate template = null;

	Database() {
		DriverManagerDataSource source = new DriverManagerDataSource();
		source.setDriverClassName(Config.DB_DRIVER);
		source.setUrl(Config.DB_ADDRESS);
		source.setUsername(Config.DB_USER);
		source.setPassword(Config.DB_PASSWD);
		template = new JdbcTemplate(source);
	}

	public JdbcTemplate getTemplate() {
		return template;
	}
}
