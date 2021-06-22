# Install MongoDB

## Standalone instance

Standalone installation

```
ansible-playbook -i hosts mongodb-dependencies.yml 
```

Optional for install kafka connect for MongoDB

```
ansible-playbook -i hosts kafka-dependencies.yml 
```

To ssh tunnel

```
ssh -L 27017:localhost:27017 ${ssh_user}@${remote_mongodb_host}
```
