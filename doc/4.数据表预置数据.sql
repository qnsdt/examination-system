-- 预置数据可以多次选中导入
-- ----------------------------
-- 初始化-部门表数据
-- ----------------------------
insert into sys_dept(dept_id,parent_id,ancestors,dept_code,dept_name,order_num,leader,phone,email,status,del_flag,create_by,create_time) values (100,0,'0','5301001','测试用单位',0,'','13512345678','','0','0','admin',sysdate);

-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
insert into SYS_USER (user_id, user_code, dept_id, user_name, nick_name, user_type, email, phonenumber, sex, avatar, password, status, del_flag, login_ip, login_date, create_by, create_time, update_by, update_time, remark)
values (2, '40288b0186e867550186e867559d0000', 100, 'user1', '用户一', null, null, '13654128596', null, null, '$2a$10$aqSRTGVSqrZFwp.s5JXic.5JtZDYFq.19hriwW7seEdPGo6SJu3.W', '0', '0', '127.0.0.1', to_date('16-03-2023 11:16:56', 'dd-mm-yyyy hh24:mi:ss'), 'admin', to_date('16-03-2023 11:11:57', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('16-03-2023 11:16:42', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SYS_USER (user_id, user_code, dept_id, user_name, nick_name, user_type, email, phonenumber, sex, avatar, password, status, del_flag, login_ip, login_date, create_by, create_time, update_by, update_time, remark)
values (3, '40288b0186e867550186e86911cc0001', 100, 'user2', '用户二', null, null, '13698568741', null, null, '$2a$10$Sh3WZ3ozH8Q88oG7mla.jerm3f3rCaq0QzD1KEPXXFrzcay0Hgz.S', '0', '0', null, null, 'admin', to_date('16-03-2023 11:13:51', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_USER (user_id, user_code, dept_id, user_name, nick_name, user_type, email, phonenumber, sex, avatar, password, status, del_flag, login_ip, login_date, create_by, create_time, update_by, update_time, remark)
values (1, 'F6FFFC4E3A50062DE0534479C80A75C1', 100, 'admin', '系统管理员', '00', 'abc@qq.com', '13512345678', '0', null, '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', to_date('16-03-2023 11:19:58', 'dd-mm-yyyy hh24:mi:ss'), 'admin', null, null, to_date('16-03-2023 11:19:44', 'dd-mm-yyyy hh24:mi:ss'), null);

-- 初始化-岗位信息表数据
-- ----------------------------
insert into sys_post (post_id,post_code,post_name,post_sort,status,del_flag,create_by,create_time) values(1, 'ceo',  '董事长',    1, '0',  '0','admin', sysdate);

-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
insert into SYS_ROLE (role_id, role_name, role_key, role_sort, data_scope, menu_check_strictly, dept_check_strictly, status, del_flag, create_by, create_time, update_by, update_time, remark)
values (2, '学员', 'student', 2, null, 1, 1, '0', '0', 'admin', to_date('16-03-2023 11:01:28', 'dd-mm-yyyy hh24:mi:ss'), 'admin', to_date('16-03-2023 11:16:25', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into SYS_ROLE (role_id, role_name, role_key, role_sort, data_scope, menu_check_strictly, dept_check_strictly, status, del_flag, create_by, create_time, update_by, update_time, remark)
values (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', to_date('16-03-2023 10:31:19', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
commit;


-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1, '系统管理', 0, 1, 'system', null, 1, 0, 'M', '0', '0', '0', null, 'system', 'admin', null, null, null, '系统管理目录');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2, '系统监控', 0, 3, 'monitor', null, 1, 0, 'M', '0', '0', '0', null, 'monitor', 'admin', null, null, null, '系统监控目录');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (3, '系统工具', 0, 4, 'tool', null, 1, 0, 'M', '0', '0', '0', null, 'tool', 'admin', null, null, null, '系统工具目录');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (8, '资料管理', 0, 8, 'fms', null, 1, 0, 'M', '0', '0', '0', null, 'files', 'admin', null, null, null, '文件管理目录');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (9, '试题管理', 0, 9, 'questions', null, 1, 0, 'M', '0', '0', '0', null, 'questions', 'admin', null, null, null, '试题管理目录');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (10, '考试管理', 0, 10, 'exammanager', null, 1, 0, 'M', '0', '0', '0', null, 'exammanager', 'admin', null, null, null, '考试管理目录');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (11, '在线考试', 0, 11, 'onlineexam', null, 1, 0, 'M', '0', '0', '0', null, 'onlineexam', 'admin', null, null, null, '在线考试目录');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (100, '用户管理', 1, 1, 'user', 'system/user/index', 1, 0, 'C', '0', '0', '0', 'system:user:list', 'user', 'admin', null, null, null, '用户管理菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (101, '角色管理', 1, 2, 'role', 'system/role/index', 1, 0, 'C', '0', '0', '0', 'system:role:list', 'peoples', 'admin', null, null, null, '角色管理菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', 1, 0, 'C', '0', '0', '0', 'system:menu:list', 'tree-table', 'admin', null, null, null, '菜单管理菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (103, '部门管理', 1, 4, 'dept', 'system/dept/index', 1, 0, 'C', '0', '0', '0', 'system:dept:list', 'tree', 'admin', null, null, null, '部门管理菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (104, '岗位管理', 1, 5, 'post', 'system/post/index', 1, 0, 'C', '0', '0', '0', 'system:post:list', 'post', 'admin', null, null, null, '岗位管理菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (105, '字典管理', 1, 6, 'dict', 'system/dict/index', 1, 0, 'C', '0', '0', '0', 'system:dict:list', 'dict', 'admin', null, null, null, '字典管理菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (106, '参数设置', 1, 7, 'config', 'system/config/index', 1, 0, 'C', '0', '0', '0', 'system:config:list', 'edit', 'admin', null, null, null, '参数设置菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (107, '通知公告', 1, 8, 'notice', 'system/notice/index', 1, 0, 'C', '0', '0', '0', 'system:notice:list', 'message', 'admin', null, null, null, '通知公告菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (108, '日志管理', 1, 9, 'log', null, 1, 0, 'M', '0', '0', '0', null, 'log', 'admin', null, null, null, '日志管理菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (109, '在线用户', 2, 1, 'online', 'monitor/online/index', 1, 0, 'C', '0', '0', '0', 'monitor:online:list', 'online', 'admin', null, null, null, '在线用户菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (110, '定时任务', 2, 2, 'job', 'monitor/job/index', 1, 0, 'C', '0', '0', '0', 'monitor:job:list', 'job', 'admin', null, null, null, '定时任务菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', 1, 0, 'C', '0', '0', '0', 'monitor:druid:list', 'druid', 'admin', null, null, null, '数据监控菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (112, '服务监控', 2, 4, 'server', 'monitor/server/index', 1, 0, 'C', '0', '0', '0', 'monitor:server:list', 'server', 'admin', null, null, null, '服务监控菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', 1, 0, 'C', '0', '0', '0', 'monitor:cache:list', 'redis', 'admin', null, null, null, '缓存监控菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (114, '表单构建', 3, 1, 'build', 'tool/build/index', 1, 0, 'C', '0', '0', '0', 'tool:build:list', 'build', 'admin', null, null, null, '表单构建菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (115, '代码生成', 3, 2, 'gen', 'tool/gen/index', 1, 0, 'C', '0', '0', '0', 'tool:gen:list', 'code', 'admin', null, null, null, '代码生成菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (116, '系统接口', 3, 3, 'swagger', 'tool/swagger/index', 1, 0, 'C', '0', '0', '0', 'tool:swagger:list', 'swagger', 'admin', null, null, null, '系统接口菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (121, '人员分组', 1, 11, 'group', 'system/group/index', 1, 0, 'C', '0', '0', null, 'system:group:list', 'group', 'admin', to_date('28-01-2023 19:40:40', 'dd-mm-yyyy hh24:mi:ss'), null, null, '人员分组菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (220, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', 1, 0, 'C', '0', '0', '0', 'monitor:operlog:list', 'form', 'admin', null, null, null, '操作日志菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (230, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', 1, 0, 'C', '0', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', null, null, null, '登录日志菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (300, '图片管理', 8, 1, 'photo', 'fms/photo/index', 1, 0, 'C', '0', '0', null, 'fms:photo:list', 'photo', 'admin', to_date('14-12-2021 15:24:20', 'dd-mm-yyyy hh24:mi:ss'), null, null, '图片管理菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (305, '文件管理', 8, 1, 'files', 'fms/files/index', 1, 0, 'C', '0', '0', null, 'fms:files:list', 'film', 'admin', to_date('17-12-2021 23:15:50', 'dd-mm-yyyy hh24:mi:ss'), null, null, '文件管理菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (320, '题库分类', 9, 1, 'examtype', 'questions/examtype/index', 1, 0, 'C', '0', '0', null, 'questions:examtype:list', 'examtype', 'admin', to_date('14-12-2022 14:28:22', 'dd-mm-yyyy hh24:mi:ss'), null, null, '考试分类菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (325, '题库设置', 9, 2, 'questionsbank', 'questions/questionsbank/index', 1, 0, 'C', '0', '0', null, 'questions:questionsbank:list', 'questionsbank', 'admin', to_date('19-12-2022 14:10:57', 'dd-mm-yyyy hh24:mi:ss'), null, null, '题库管理菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (330, '考试题目管理', 9, 5, 'examquestions', 'questions/examquestions/index', 1, 0, 'C', '0', '0', null, 'questions:examquestions:list', 'examquestions', 'admin', to_date('27-12-2022 20:08:00', 'dd-mm-yyyy hh24:mi:ss'), null, null, '考试题目管理菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (350, '创建考试', 10, 1, 'examtask', 'exam/examtask/index', 1, 0, 'C', '0', '0', null, 'exam:examtask:list', 'examtask', 'admin', to_date('06-01-2023 13:53:00', 'dd-mm-yyyy hh24:mi:ss'), null, null, '创建考试菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (360, '开始考试', 11, 1, 'examstart', 'onlineexam/examstart/index', 1, 0, 'C', '0', '0', null, 'onlineexam:data:list', 'examstart', 'admin', to_date('06-01-2023 13:53:00', 'dd-mm-yyyy hh24:mi:ss'), null, null, '开始考试菜单');
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1001, '用户查询', 100, 1, null, null, 1, 0, 'F', '0', '0', '0', 'system:user:query', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1002, '用户新增', 100, 2, null, null, 1, 0, 'F', '0', '0', '0', 'system:user:add', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1003, '用户修改', 100, 3, null, null, 1, 0, 'F', '0', '0', '0', 'system:user:edit', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1004, '用户删除', 100, 4, null, null, 1, 0, 'F', '0', '0', '0', 'system:user:remove', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1005, '用户导出', 100, 5, null, null, 1, 0, 'F', '0', '0', '0', 'system:user:export', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1006, '用户导入', 100, 6, null, null, 1, 0, 'F', '0', '0', '0', 'system:user:import', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1007, '重置密码', 100, 7, null, null, 1, 0, 'F', '0', '0', '0', 'system:user:resetPwd', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1008, '角色查询', 101, 1, null, null, 1, 0, 'F', '0', '0', '0', 'system:role:query', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1009, '角色新增', 101, 2, null, null, 1, 0, 'F', '0', '0', '0', 'system:role:add', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1010, '角色修改', 101, 3, null, null, 1, 0, 'F', '0', '0', '0', 'system:role:edit', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1011, '角色删除', 101, 4, null, null, 1, 0, 'F', '0', '0', '0', 'system:role:remove', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1012, '角色导出', 101, 5, null, null, 1, 0, 'F', '0', '0', '0', 'system:role:export', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1013, '菜单查询', 102, 1, null, null, 1, 0, 'F', '0', '0', '0', 'system:menu:query', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1014, '菜单新增', 102, 2, null, null, 1, 0, 'F', '0', '0', '0', 'system:menu:add', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1015, '菜单修改', 102, 3, null, null, 1, 0, 'F', '0', '0', '0', 'system:menu:edit', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1016, '菜单删除', 102, 4, null, null, 1, 0, 'F', '0', '0', '0', 'system:menu:remove', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1017, '部门查询', 103, 1, null, null, 1, 0, 'F', '0', '0', '0', 'system:dept:query', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1018, '部门新增', 103, 2, null, null, 1, 0, 'F', '0', '0', '0', 'system:dept:add', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1019, '部门修改', 103, 3, null, null, 1, 0, 'F', '0', '0', '0', 'system:dept:edit', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1020, '部门删除', 103, 4, null, null, 1, 0, 'F', '0', '0', '0', 'system:dept:remove', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1021, '岗位查询', 104, 1, null, null, 1, 0, 'F', '0', '0', '0', 'system:post:query', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1022, '岗位新增', 104, 2, null, null, 1, 0, 'F', '0', '0', '0', 'system:post:add', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1023, '岗位修改', 104, 3, null, null, 1, 0, 'F', '0', '0', '0', 'system:post:edit', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1024, '岗位删除', 104, 4, null, null, 1, 0, 'F', '0', '0', '0', 'system:post:remove', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1025, '岗位导出', 104, 5, null, null, 1, 0, 'F', '0', '0', '0', 'system:post:export', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1026, '字典查询', 105, 1, '#', null, 1, 0, 'F', '0', '0', '0', 'system:dict:query', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1027, '字典新增', 105, 2, '#', null, 1, 0, 'F', '0', '0', '0', 'system:dict:add', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1028, '字典修改', 105, 3, '#', null, 1, 0, 'F', '0', '0', '0', 'system:dict:edit', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1029, '字典删除', 105, 4, '#', null, 1, 0, 'F', '0', '0', '0', 'system:dict:remove', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1030, '字典导出', 105, 5, '#', null, 1, 0, 'F', '0', '0', '0', 'system:dict:export', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1031, '参数查询', 106, 1, '#', null, 1, 0, 'F', '0', '0', '0', 'system:config:query', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1032, '参数新增', 106, 2, '#', null, 1, 0, 'F', '0', '0', '0', 'system:config:add', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1033, '参数修改', 106, 3, '#', null, 1, 0, 'F', '0', '0', '0', 'system:config:edit', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1034, '参数删除', 106, 4, '#', null, 1, 0, 'F', '0', '0', '0', 'system:config:remove', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1035, '参数导出', 106, 5, '#', null, 1, 0, 'F', '0', '0', '0', 'system:config:export', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1036, '公告查询', 107, 1, '#', null, 1, 0, 'F', '0', '0', '0', 'system:notice:query', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1037, '公告新增', 107, 2, '#', null, 1, 0, 'F', '0', '0', '0', 'system:notice:add', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1038, '公告修改', 107, 3, '#', null, 1, 0, 'F', '0', '0', '0', 'system:notice:edit', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1039, '公告删除', 107, 4, '#', null, 1, 0, 'F', '0', '0', '0', 'system:notice:remove', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1040, '操作查询', 220, 1, '#', null, 1, 0, 'F', '0', '0', '0', 'monitor:operlog:query', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1041, '操作删除', 220, 2, '#', null, 1, 0, 'F', '0', '0', '0', 'monitor:operlog:remove', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1042, '日志导出', 220, 4, '#', null, 1, 0, 'F', '0', '0', '0', 'monitor:operlog:export', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1043, '登录查询', 230, 1, '#', null, 1, 0, 'F', '0', '0', '0', 'monitor:logininfor:query', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1044, '登录删除', 230, 2, '#', null, 1, 0, 'F', '0', '0', '0', 'monitor:logininfor:remove', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1045, '日志导出', 230, 3, '#', null, 1, 0, 'F', '0', '0', '0', 'monitor:logininfor:export', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1046, '在线查询', 109, 1, '#', null, 1, 0, 'F', '0', '0', '0', 'monitor:online:query', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1047, '批量强退', 109, 2, '#', null, 1, 0, 'F', '0', '0', '0', 'monitor:online:batchLogout', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1048, '单条强退', 109, 3, '#', null, 1, 0, 'F', '0', '0', '0', 'monitor:online:forceLogout', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1049, '任务查询', 110, 1, '#', null, 1, 0, 'F', '0', '0', '0', 'monitor:job:query', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1050, '任务新增', 110, 2, '#', null, 1, 0, 'F', '0', '0', '0', 'monitor:job:add', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1051, '任务修改', 110, 3, '#', null, 1, 0, 'F', '0', '0', '0', 'monitor:job:edit', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1052, '任务删除', 110, 4, '#', null, 1, 0, 'F', '0', '0', '0', 'monitor:job:remove', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1053, '状态修改', 110, 5, '#', null, 1, 0, 'F', '0', '0', '0', 'monitor:job:changeStatus', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1054, '任务导出', 110, 7, '#', null, 1, 0, 'F', '0', '0', '0', 'monitor:job:export', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1055, '生成查询', 115, 1, '#', null, 1, 0, 'F', '0', '0', '0', 'tool:gen:query', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1056, '生成修改', 115, 2, '#', null, 1, 0, 'F', '0', '0', '0', 'tool:gen:edit', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1057, '生成删除', 115, 3, '#', null, 1, 0, 'F', '0', '0', '0', 'tool:gen:remove', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1058, '导入代码', 115, 2, '#', null, 1, 0, 'F', '0', '0', '0', 'tool:gen:import', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1059, '预览代码', 115, 4, '#', null, 1, 0, 'F', '0', '0', '0', 'tool:gen:preview', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1060, '生成代码', 115, 5, '#', null, 1, 0, 'F', '0', '0', '0', 'tool:gen:code', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1061, '部门导入', 103, 5, null, null, 1, 0, 'F', '0', '0', '0', 'system:dept:wechatin', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1062, '用户导入', 100, 7, null, null, 1, 0, 'F', '0', '0', '0', 'system:user:wechatin', '#', 'admin', null, null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1080, '人员分组查询', 121, 1, '#', null, 1, 0, 'F', '0', '0', null, 'system:group:query', '#', 'admin', to_date('28-01-2023 20:02:09', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1081, '人员分组新增', 121, 2, '#', null, 1, 0, 'F', '0', '0', null, 'system:group:add', '#', 'admin', to_date('28-01-2023 20:02:09', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1082, '人员分组修改', 121, 3, '#', null, 1, 0, 'F', '0', '0', null, 'system:group:edit', '#', 'admin', to_date('28-01-2023 20:02:09', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1083, '人员分组删除', 121, 4, '#', null, 1, 0, 'F', '0', '0', null, 'system:group:remove', '#', 'admin', to_date('28-01-2023 20:02:10', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1084, '人员分组导出', 121, 5, '#', null, 1, 0, 'F', '0', '0', null, 'system:group:export', '#', 'admin', to_date('28-01-2023 20:02:10', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1390, '图片管理查询', 300, 1, '#', null, 1, 0, 'F', '0', '0', null, 'fms:photo:query', '#', 'admin', to_date('14-12-2021 15:28:21', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1391, '图片管理新增', 300, 2, '#', null, 1, 0, 'F', '0', '0', null, 'fms:photo:add', '#', 'admin', to_date('14-12-2021 15:28:21', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1392, '图片管理修改', 300, 3, '#', null, 1, 0, 'F', '0', '0', null, 'fms:photo:edit', '#', 'admin', to_date('14-12-2021 15:28:21', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1393, '图片管理删除', 300, 4, '#', null, 1, 0, 'F', '0', '0', null, 'fms:photo:remove', '#', 'admin', to_date('14-12-2021 15:28:21', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1394, '图片管理下载', 300, 5, '#', null, 1, 0, 'F', '0', '0', null, 'fms:photo:export', '#', 'admin', to_date('14-12-2021 15:28:21', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1420, '文件管理查询', 305, 1, '#', null, 1, 0, 'F', '0', '0', null, 'fms:files:query', '#', 'admin', to_date('17-12-2021 23:18:56', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1421, '文件管理新增', 305, 2, '#', null, 1, 0, 'F', '0', '0', null, 'fms:files:add', '#', 'admin', to_date('17-12-2021 23:18:56', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1422, '文件管理修改', 305, 3, '#', null, 1, 0, 'F', '0', '0', null, 'fms:files:edit', '#', 'admin', to_date('17-12-2021 23:18:56', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1423, '文件管理删除', 305, 4, '#', null, 1, 0, 'F', '0', '0', null, 'fms:files:remove', '#', 'admin', to_date('17-12-2021 23:18:56', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1424, '文件管理下载', 305, 5, '#', null, 1, 0, 'F', '0', '0', null, 'fms:files:export', '#', 'admin', to_date('17-12-2021 23:18:57', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1501, '考试分类查询', 320, 1, '#', null, 1, 0, 'F', '0', '0', null, 'questions:examtype:query', '#', 'admin', to_date('14-12-2022 14:32:15', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1502, '考试分类新增', 320, 2, '#', null, 1, 0, 'F', '0', '0', null, 'questions:examtype:add', '#', 'admin', to_date('14-12-2022 14:32:15', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1503, '考试分类修改', 320, 3, '#', null, 1, 0, 'F', '0', '0', null, 'questions:examtype:edit', '#', 'admin', to_date('14-12-2022 14:32:15', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1504, '考试分类删除', 320, 4, '#', null, 1, 0, 'F', '0', '0', null, 'questions:examtype:remove', '#', 'admin', to_date('14-12-2022 14:32:16', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1505, '考试分类导出', 320, 5, '#', null, 1, 0, 'F', '0', '0', null, 'questions:examtype:export', '#', 'admin', to_date('14-12-2022 14:32:16', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1525, '题库管理查询', 325, 1, '#', null, 1, 0, 'F', '0', '0', null, 'questions:questionsbank:query', '#', 'admin', to_date('19-12-2022 14:10:57', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1526, '题库管理新增', 325, 2, '#', null, 1, 0, 'F', '0', '0', null, 'questions:questionsbank:add', '#', 'admin', to_date('19-12-2022 14:10:57', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1527, '题库管理修改', 325, 3, '#', null, 1, 0, 'F', '0', '0', null, 'questions:questionsbank:edit', '#', 'admin', to_date('19-12-2022 14:10:58', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1528, '题库管理删除', 325, 4, '#', null, 1, 0, 'F', '0', '0', null, 'questions:questionsbank:remove', '#', 'admin', to_date('19-12-2022 14:10:58', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1529, '题库管理导出', 325, 5, '#', null, 1, 0, 'F', '0', '0', null, 'questions:questionsbank:export', '#', 'admin', to_date('19-12-2022 14:10:58', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1541, '考试题目查询', 330, 1, '#', null, 1, 0, 'F', '0', '0', null, 'questions:examquestions:query', '#', 'admin', to_date('27-12-2022 20:10:55', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1542, '考试题目新增', 330, 2, '#', null, 1, 0, 'F', '0', '0', null, 'questions:examquestions:add', '#', 'admin', to_date('27-12-2022 20:10:55', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1543, '考试题目修改', 330, 3, '#', null, 1, 0, 'F', '0', '0', null, 'questions:examquestions:edit', '#', 'admin', to_date('27-12-2022 20:10:56', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1544, '考试题目删除', 330, 4, '#', null, 1, 0, 'F', '0', '0', null, 'questions:examquestions:remove', '#', 'admin', to_date('27-12-2022 20:10:56', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1545, '考试题目导出', 330, 5, '#', null, 1, 0, 'F', '0', '0', null, 'questions:examquestions:export', '#', 'admin', to_date('27-12-2022 20:10:56', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1601, '创建考试查询', 350, 1, '#', null, 1, 0, 'F', '0', '0', null, 'exam:examtask:query', '#', 'admin', to_date('06-01-2023 13:56:00', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1602, '创建考试新增', 350, 2, '#', null, 1, 0, 'F', '0', '0', null, 'exam:examtask:add', '#', 'admin', to_date('06-01-2023 13:56:01', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1603, '创建考试修改', 350, 3, '#', null, 1, 0, 'F', '0', '0', null, 'exam:examtask:edit', '#', 'admin', to_date('06-01-2023 13:56:01', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1604, '创建考试删除', 350, 4, '#', null, 1, 0, 'F', '0', '0', null, 'exam:examtask:remove', '#', 'admin', to_date('06-01-2023 13:56:01', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into SYS_MENU (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, del_flag, perms, icon, create_by, create_time, update_by, update_time, remark)
values (1605, '创建考试导出', 350, 5, '#', null, 1, 0, 'F', '0', '0', null, 'exam:examtask:export', '#', 'admin', to_date('06-01-2023 13:56:01', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
commit;


-- ----------------------------
-- 初始化-用户和角色关联表数据
-- ----------------------------
insert into SYS_USER_ROLE (user_id, role_id)
values (1, 1);
insert into SYS_USER_ROLE (user_id, role_id)
values (2, 2);
insert into SYS_USER_ROLE (user_id, role_id)
values (3, 2);

-- ----------------------------
-- 初始化-角色和菜单关联表数据
-- ----------------------------
insert into sys_role_menu values ('2', '1');
insert into sys_role_menu values ('2', '2');
insert into sys_role_menu values ('2', '3');
insert into sys_role_menu values ('2', '4');
insert into sys_role_menu values ('2', '100');
insert into sys_role_menu values ('2', '101');
insert into sys_role_menu values ('2', '102');
insert into sys_role_menu values ('2', '103');
insert into sys_role_menu values ('2', '104');
insert into sys_role_menu values ('2', '105');
insert into sys_role_menu values ('2', '106');
insert into sys_role_menu values ('2', '107');
insert into sys_role_menu values ('2', '108');
insert into sys_role_menu values ('2', '109');
insert into sys_role_menu values ('2', '110');
insert into sys_role_menu values ('2', '111');
insert into sys_role_menu values ('2', '112');
insert into sys_role_menu values ('2', '113');
insert into sys_role_menu values ('2', '114');
insert into sys_role_menu values ('2', '115');
insert into sys_role_menu values ('2', '116');
insert into sys_role_menu values ('2', '500');
insert into sys_role_menu values ('2', '501');
insert into sys_role_menu values ('2', '1000');
insert into sys_role_menu values ('2', '1001');
insert into sys_role_menu values ('2', '1002');
insert into sys_role_menu values ('2', '1003');
insert into sys_role_menu values ('2', '1004');
insert into sys_role_menu values ('2', '1005');
insert into sys_role_menu values ('2', '1006');
insert into sys_role_menu values ('2', '1007');
insert into sys_role_menu values ('2', '1008');
insert into sys_role_menu values ('2', '1009');
insert into sys_role_menu values ('2', '1010');
insert into sys_role_menu values ('2', '1011');
insert into sys_role_menu values ('2', '1012');
insert into sys_role_menu values ('2', '1013');
insert into sys_role_menu values ('2', '1014');
insert into sys_role_menu values ('2', '1015');
insert into sys_role_menu values ('2', '1016');
insert into sys_role_menu values ('2', '1017');
insert into sys_role_menu values ('2', '1018');
insert into sys_role_menu values ('2', '1019');
insert into sys_role_menu values ('2', '1020');
insert into sys_role_menu values ('2', '1021');
insert into sys_role_menu values ('2', '1022');
insert into sys_role_menu values ('2', '1023');
insert into sys_role_menu values ('2', '1024');
insert into sys_role_menu values ('2', '1025');
insert into sys_role_menu values ('2', '1026');
insert into sys_role_menu values ('2', '1027');
insert into sys_role_menu values ('2', '1028');
insert into sys_role_menu values ('2', '1029');
insert into sys_role_menu values ('2', '1030');
insert into sys_role_menu values ('2', '1031');
insert into sys_role_menu values ('2', '1032');
insert into sys_role_menu values ('2', '1033');
insert into sys_role_menu values ('2', '1034');
insert into sys_role_menu values ('2', '1035');
insert into sys_role_menu values ('2', '1036');
insert into sys_role_menu values ('2', '1037');
insert into sys_role_menu values ('2', '1038');
insert into sys_role_menu values ('2', '1039');
insert into sys_role_menu values ('2', '1040');
insert into sys_role_menu values ('2', '1041');
insert into sys_role_menu values ('2', '1042');
insert into sys_role_menu values ('2', '1043');
insert into sys_role_menu values ('2', '1044');
insert into sys_role_menu values ('2', '1045');
insert into sys_role_menu values ('2', '1046');
insert into sys_role_menu values ('2', '1047');
insert into sys_role_menu values ('2', '1048');
insert into sys_role_menu values ('2', '1049');
insert into sys_role_menu values ('2', '1050');
insert into sys_role_menu values ('2', '1051');
insert into sys_role_menu values ('2', '1052');
insert into sys_role_menu values ('2', '1053');
insert into sys_role_menu values ('2', '1054');
insert into sys_role_menu values ('2', '1055');
insert into sys_role_menu values ('2', '1056');
insert into sys_role_menu values ('2', '1057');
insert into sys_role_menu values ('2', '1058');
insert into sys_role_menu values ('2', '1059');
insert into sys_role_menu values ('2', '1060');

-- ----------------------------
-- 初始化-角色和部门关联表数据
-- ----------------------------
insert into sys_role_dept values ('2', '100');
insert into sys_role_dept values ('2', '101');
insert into sys_role_dept values ('2', '105');

-- ----------------------------
-- 初始化-用户与岗位关联表数据
-- ----------------------------
insert into sys_user_post values ('1', '1');
insert into sys_user_post values ('2', '2');

-- ----------------------------
-- 初始化-字典类型表
-- ----------------------------
insert into sys_dict_type values(1,  '用户性别', 'sys_user_sex',        '0', 'admin', sysdate, '', null, '用户性别列表');
insert into sys_dict_type values(2,  '菜单状态', 'sys_show_hide',       '0', 'admin', sysdate, '', null, '菜单状态列表');
insert into sys_dict_type values(3,  '系统开关', 'sys_normal_disable',  '0', 'admin', sysdate, '', null, '系统开关列表');
insert into sys_dict_type values(4,  '任务状态', 'sys_job_status',      '0', 'admin', sysdate, '', null, '任务状态列表');
insert into sys_dict_type values(5,  '任务分组', 'sys_job_group',       '0', 'admin', sysdate, '', null, '任务分组列表');
insert into sys_dict_type values(6,  '系统是否', 'sys_yes_no',          '0', 'admin', sysdate, '', null, '系统是否列表');
insert into sys_dict_type values(7,  '通知类型', 'sys_notice_type',     '0', 'admin', sysdate, '', null, '通知类型列表');
insert into sys_dict_type values(8,  '通知状态', 'sys_notice_status',   '0', 'admin', sysdate, '', null, '通知状态列表');
insert into sys_dict_type values(9,  '操作类型', 'sys_oper_type',       '0', 'admin', sysdate, '', null, '操作类型列表');
insert into sys_dict_type values(10, '系统状态', 'sys_common_status',   '0', 'admin', sysdate, '', null, '登录状态列表');

-- ----------------------------
-- 初始化-字典数据表
-- ----------------------------
insert into sys_dict_data values(1,  1,  '男',       '0',       'sys_user_sex',        '',   '',        'Y', '0', 'admin', sysdate, '', null, '性别男');
insert into sys_dict_data values(2,  2,  '女',       '1',       'sys_user_sex',        '',   '',        'N', '0', 'admin', sysdate, '', null, '性别女');
insert into sys_dict_data values(3,  3,  '未知',     '2',       'sys_user_sex',        '',   '',        'N', '0', 'admin', sysdate, '', null, '性别未知');
insert into sys_dict_data values(4,  1,  '显示',     '0',       'sys_show_hide',       '',   'primary', 'Y', '0', 'admin', sysdate, '', null, '显示菜单');
insert into sys_dict_data values(5,  2,  '隐藏',     '1',       'sys_show_hide',       '',   'danger',  'N', '0', 'admin', sysdate, '', null, '隐藏菜单');
insert into sys_dict_data values(6,  1,  '正常',     '0',       'sys_normal_disable',  '',   'primary', 'Y', '0', 'admin', sysdate, '', null, '正常状态');
insert into sys_dict_data values(7,  2,  '停用',     '1',       'sys_normal_disable',  '',   'danger',  'N', '0', 'admin', sysdate, '', null, '停用状态');
insert into sys_dict_data values(8,  1,  '正常',     '0',       'sys_job_status',      '',   'primary', 'Y', '0', 'admin', sysdate, '', null, '正常状态');
insert into sys_dict_data values(9,  2,  '暂停',     '1',       'sys_job_status',      '',   'danger',  'N', '0', 'admin', sysdate, '', null, '停用状态');
insert into sys_dict_data values(10, 1,  '默认',     'DEFAULT', 'sys_job_group',       '',   '',        'Y', '0', 'admin', sysdate, '', null, '默认分组');
insert into sys_dict_data values(11, 2,  '系统',     'SYSTEM',  'sys_job_group',       '',   '',        'N', '0', 'admin', sysdate, '', null, '系统分组');
insert into sys_dict_data values(12, 1,  '是',       'Y',       'sys_yes_no',          '',   'primary', 'Y', '0', 'admin', sysdate, '', null, '系统默认是');
insert into sys_dict_data values(13, 2,  '否',       'N',       'sys_yes_no',          '',   'danger',  'N', '0', 'admin', sysdate, '', null, '系统默认否');
insert into sys_dict_data values(14, 1,  '通知',     '1',       'sys_notice_type',     '',   'warning', 'Y', '0', 'admin', sysdate, '', null, '通知');
insert into sys_dict_data values(15, 2,  '公告',     '2',       'sys_notice_type',     '',   'success', 'N', '0', 'admin', sysdate, '', null, '公告');
insert into sys_dict_data values(16, 1,  '正常',     '0',       'sys_notice_status',   '',   'primary', 'Y', '0', 'admin', sysdate, '', null, '正常状态');
insert into sys_dict_data values(17, 2,  '关闭',     '1',       'sys_notice_status',   '',   'danger',  'N', '0', 'admin', sysdate, '', null, '关闭状态');
insert into sys_dict_data values(18, 1,  '新增',     '1',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', sysdate, '', null, '新增操作');
insert into sys_dict_data values(19, 2,  '修改',     '2',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', sysdate, '', null, '修改操作');
insert into sys_dict_data values(20, 3,  '删除',     '3',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', sysdate, '', null, '删除操作');
insert into sys_dict_data values(21, 4,  '授权',     '4',       'sys_oper_type',       '',   'primary', 'N', '0', 'admin', sysdate, '', null, '授权操作');
insert into sys_dict_data values(22, 5,  '导出',     '5',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', sysdate, '', null, '导出操作');
insert into sys_dict_data values(23, 6,  '导入',     '6',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', sysdate, '', null, '导入操作');
insert into sys_dict_data values(24, 7,  '强退',     '7',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', sysdate, '', null, '强退操作');
insert into sys_dict_data values(25, 8,  '生成代码', '8',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', sysdate, '', null, '生成操作');
insert into sys_dict_data values(26, 9,  '清空数据', '9',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', sysdate, '', null, '清空操作');
insert into sys_dict_data values(27, 1,  '成功',     '0',       'sys_common_status',   '',   'primary', 'N', '0', 'admin', sysdate, '', null, '正常状态');
insert into sys_dict_data values(28, 2,  '失败',     '1',       'sys_common_status',   '',   'danger',  'N', '0', 'admin', sysdate, '', null, '停用状态');

-- ----------------------------
-- 初始化-参数配置表
-- ----------------------------
insert into sys_config values(1, '主框架页-默认皮肤样式名称', 'sys.index.skinName',            'skin-blue',     'Y', 'admin', sysdate, '', null, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow' );
insert into sys_config values(2, '用户管理-账号初始密码',     'sys.user.initPassword',         '123456',        'Y', 'admin', sysdate, '', null, '初始化密码 123456' );
insert into sys_config values(3, '主框架页-侧边栏主题',       'sys.index.sideTheme',           'theme-dark',    'Y', 'admin', sysdate, '', null, '深色主题theme-dark，浅色主题theme-light' );
insert into sys_config values(4, '账号自助-验证码开关',       'sys.account.captchaOnOff',      'true',          'Y', 'admin', sysdate, '', null, '是否开启登录验证码功能（true开启，false关闭）');

-- ----------------------------
-- 初始化-公告信息表数据
-- ----------------------------
insert into sys_notice values('1', '温馨提醒：2018-07-01 新版本发布啦', '2', '新版本内容', '0', 'admin', sysdate, '', null, '管理员');
insert into sys_notice values('2', '维护通知：2018-07-01 系统凌晨维护', '1', '维护内容',   '0', 'admin', sysdate, '', null, '管理员');
commit;

