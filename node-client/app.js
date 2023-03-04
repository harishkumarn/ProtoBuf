const grpc = require("grpc");
var protoLoader = require("@grpc/proto-loader");

const PROTO_PATH = "./proto/unary.proto";

var packageDefinition = protoLoader.loadSync(PROTO_PATH);

const clientStub = grpc.loadPackageDefinition(packageDefinition).Server;

const client = new clientStub("localhost:1234",grpc.credentials.createInsecure());
switch(process.argv[2]){
	case "unary":
		client.getGreeting({language:"Eng",name:"Harish Kumar N"},(error, greeting)=>{
			if(greeting){
				console.log(greeting);
			}else{
				console.log(error);
			}});
		break;
	case "client_stream":
		var call  = client.greetAllAtOnce((err, resp)=>{
			if(resp){
				console.log(resp);
			}else{
				console.log(err);
			}
		});
		for(var i = 0 ; i < 4; ++i){
			call.write({language:"Tam",name:"Harish Kumar N " + i.toString()});
		}
		call.end();
		break;
	case "server_stream":
		var call = client.getGreetings({language:"",name:"Harish Kumar N"});
		call.on("data",console.log)
		call.on("error",console.log);
		call.on("end",()=>{
			console.log("Saynoara !!");
		});
		break;
	case "bidirectional_stream":
		break;
};
