package spring12_rmi_server.service;

import spring12_rmi_server.bean.User;

public interface UserService {

	User getUser(String name);
}
