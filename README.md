# Workflow-backend


后端设计

框架

- 基于Maven和Intellij IDEA的SpringBoot + Hibernate框架
- 框架搭建参考地址：https://blog.csdn.net/Winter_chen001/article/details/80537829



数据库设计

User

- user_id
- user_name
- user_number  学号
- user_major  专业
- user_grade  年级
- user_phone
- user_email
- user_specialty  特长
- user_resume  用户的简历（图片）

Activity （包括各种活动和比赛）

- activity_id
- activity_name
- activity_time
- activity_place
- activity_description
- activity_signup_deadline  活动报名截止日期

Recruit

- recruit_id
- recruit_name
- recruit_position  招聘职位
- recruit_description
- recruit_state
- recruit_willing_number  待招人数
- recruit_registered_number  已招人数
- user_id  外码   招聘的创建者
- activity_id   外码  

User_Recruit

- user_recruit_id
- user_id    外码    关注/应聘该条招聘的用户
- recruit_id    外码
- recruit_follower  是否为该条招聘的关注者
- recruit_applicant  是否为该条招聘的应聘者
- recruit_result  是否应聘成功

Tag（标签）

- tag_id
- tag_name

User_Tag

- user_id  外码
- tag_id  外码

Activity_Tag

- activity_id  外码
- tag_id  外码

Recruit_Tag

- recruit_id  外码
- tag_id  外码

Tree

- tree_id
- tree_name
- activity_name
- user_id    外码  整个节点树的创建者

Task

- task_id  
- task_name
- user_id  外码  该节点（任务）的创建者
- tree_id  外码   该节点（任务）所属的节点树  
- task_description
- task_create_time
- parent_task_id  外码   该节点（任务）的父节点

Task_Log

- task_log_id
- task_id
- user_id   外码  本次对任务节点进行编辑的用户
- task_verison
- task_state
- task_remark
- task_modify_time

Date_Event

- date_event_id
- date
- event_name
- event_description

标签推荐算法设计

- 参考地址：https://www.jianshu.com/p/1e5608666d20
