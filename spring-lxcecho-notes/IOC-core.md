DefaultListableBeanFactory 是非常强大的，支持很多功能，可以通过查看 DefaultListableBeanFactory 的类继承实现结构来看:

![img.png](img.png)

AnnotationConfigApplicationContext 类继承实现结构：

![img_1.png](img_1.png)

1. ConfigurableApplicationContext：继承了ApplicationContext接口，增加了，添加事件监听器、添加 BeanFactoryPostProcessor、设置 Environment，获取 ConfigurableListableBeanFactory 等功能； 
2. AbstractApplicationContext：实现了 ConfigurableApplicationContext 接口；
3. GenericApplicationContext：继承了 AbstractApplicationContext，实现了 BeanDefinitionRegistry 接口，拥有了所有 ApplicationContext 的功能，并且可以注册 BeanDefinition，注意这个类中有一个属性 (DefaultListableBeanFactory beanFactory)； 
4. AnnotationConfigRegistry：可以单独注册某个为类为 BeanDefinition（可以处理该类上的 **@Configuration** 注解，已经可以处理 **@Bean 注解**），同时可以扫描； 
5. AnnotationConfigApplicationContext：继承了 GenericApplicationContext，实现了 AnnotationConfigRegistry 接口，拥有了以上所有的功能；

ClassPathXmlApplicationContext 类继承实现结构：它也是继承了 AbstractApplicationContext，但是相对于AnnotationConfigApplicationContext 而言，功能没有 AnnotationConfigApplicationContext 强大，比如不能注册 BeanDefinition。

![img_2.png](img_2.png)